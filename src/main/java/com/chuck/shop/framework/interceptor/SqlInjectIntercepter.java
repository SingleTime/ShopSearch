/**
 * SqlInjectIntercepter.java 
 * Copyright © 2009-2015, 杭州跃度网络科技有限公司
 * 
 * @author liao
 * @create 2015年2月3日
 */
package com.chuck.shop.framework.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 */

public class SqlInjectIntercepter extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger.getLogger(SqlInjectIntercepter.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String[] values = request.getParameterValues(name);
			for (int i = 0; i < values.length; i++) {
				values[i] = StringEscapeUtils.escapeSql(values[i]);
			}
		}
		return true;
	}

}