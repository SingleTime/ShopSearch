/**
 * EmojiFilter.java 
 * Copyright © 2009-2013, 杭州跃度网络科技有限公司
 * 
 * @author Liu Tao
 * @create 2014年7月2日
 */
package com.chuck.shop.framework.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.log4j.Logger;

import com.chuck.shop.common.utils.PropertyLoader;
import com.chuck.shop.common.utils.StringUtils;
import com.chuck.shop.constant.ShopConstants;

/**
 * 提交文字过滤
 */
public class InputFilter implements Filter {

	private static final Logger logger = Logger.getLogger(InputFilter.class);
	private static Properties props = null;
	private static final String BLACKLIST_FILE = "blacklist.properties";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		props = PropertyLoader.getProperties(BLACKLIST_FILE);
		logger.info("敏感词文件加载成功");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		final String method = req.getMethod();
		if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT")) {
			InputWordRequestWrapper wrapper = new InputWordRequestWrapper((HttpServletRequest) request, props);
			chain.doFilter(wrapper, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

	}

	public class InputWordRequestWrapper extends HttpServletRequestWrapper {

		private Properties props;

		public InputWordRequestWrapper(HttpServletRequest request, Properties props) {
			super(request);
			this.props = props;
		}

		@Override
		public Map getParameterMap() {
			super.getContextPath();
			Map<String, String[]> map = super.getParameterMap();
			if (!map.isEmpty()) {
				Set<String> keySet = map.keySet();
				Iterator<String> keyIt = keySet.iterator();
				while (keyIt.hasNext()) {
					String key = keyIt.next();
					String[] values = map.get(key);
					for (int i = 0; i < values.length; i++) {
						map.get(key)[i] = this.replaceParam(values[i]);
					}
				}
			}
			return map;
		}

		@Override
		public String getParameter(String str) {
			String s = super.getParameter(str);
			return replaceParam(s);
		}

		@Override
		public String[] getParameterValues(String str) {
			String[] ss = super.getParameterValues(str);
			if (ss == null || ss.length == 0) {
				return ss;
			}
			String[] ss2 = new String[ss.length];
			for (int i = 0; i < ss2.length; i++) {
				ss2[i] = replaceParam(ss[i]);
			}
			return ss2;
		}

		private String replaceParam(String string) {
			if (StringUtils.isEmpty(string)) {
				return string;
			}
			String sb = new String(string);
			Enumeration en = props.propertyNames();
			while (en.hasMoreElements()) {
				String s = en.nextElement().toString();
				if (string.contains(s)) {
					sb = sb.replaceAll(s, props.getProperty(s));
				}
			}

			sb = sb.replaceAll("\\\\r|\\\\n|\\\\t", "");
			sb = filteEmoji(sb);
			return sb;
		}

	}

	/**
	 * 检测是否有emoji字符
	 * 
	 * @param source
	 * @return 一旦含有就抛出
	 */
	public static boolean containsEmoji(String source) {
		if (StringUtils.isBlank(source)) {
			return false;
		}

		int len = source.length();
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
			if (!isNotEmojiCharacter(codePoint)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isNotEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	static Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
			Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

	/**
	 * 过滤emoji 或者 其他非文字类型的字符
	 * 
	 * @param source
	 * @return
	 */
	public static String filteEmoji(String source) {
		if (!containsEmoji(source)) {
			return source;// 如果不包含，直接返回
		}

		StringBuilder buf = null;
		int len = source.length();
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
			if (isNotEmojiCharacter(codePoint)) {
				if (buf == null) {
					buf = new StringBuilder(source.length());
				}
				buf.append(codePoint);
			}
		}

		if (buf == null) {
			return ShopConstants.EMPTY;
		} else {
			if (buf.length() == len) {
				buf = null;
				return source;
			} else {
				return buf.toString();
			}
		}
	}

}
