/**
 * XSSSecurityFilter.java 
 * Copyright © 2009-2015, 杭州跃度网络科技有限公司
 * 
 * @author liao
 * @create 2015年2月3日
 */
package com.chuck.shop.framework.XSSFilter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.chuck.shop.common.utils.StringUtils;
import com.chuck.shop.constant.ErrorMsg;

/**
 * @describe 安全信息审核类
 */
public class XSSSecurityFilter implements Filter {

	private static Logger logger = Logger.getLogger(XSSSecurityFilter.class);
	
	/**
	 * 销毁操作
	 */
	public void destroy() {
		logger.info("XSSSecurityFilter destroy() begin");
		XSSSecurityManager.destroy();
		logger.info("XSSSecurityFilter destroy() end");
	}

	/**
	 * 安全审核
	 * 读取配置信息
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		// 判断是否使用HTTP
        checkRequestResponse(request, response);
        // 转型
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String xmlhttprequest = httpRequest.getHeader("X-Requested-With");
        
        // http信息封装类
		XSSHttpRequestWrapper xssRequest = new XSSHttpRequestWrapper(httpRequest);
		logger.debug(String.format("***** 正在检测请求是否合法。发起IP：%1$s，RequestHeader：%2$s。", 
                httpRequest.getRemoteAddr(), StringUtils.isBlank(xmlhttprequest) ? "Normal" : xmlhttprequest));
        // 对request信息进行封装并进行校验工作，若校验失败（含非法字符），根据配置信息进行日志记录和请求中断处理
        if(xssRequest.validateParameter()) {
        	if(XSSSecurityConfig.IS_LOG){
        		// 记录攻击访问日志
        		// 可使用数据库、日志、文件等方式
        	    String path = httpRequest.getContextPath();
                String url = httpRequest.getRequestURI().substring(path.length());
        	    logger.warn(String.format(" !!!!! [警告] 检测到有非法请求，请求参数不合法，可能属于XSS攻击。发起IP：%1$s，请求地址：%2$s，RequestHeader：%3$s。已经被拦截！", 
        	            httpRequest.getRemoteAddr(), url, StringUtils.isBlank(xmlhttprequest) ? "Normal" : xmlhttprequest));
        	}
        	//过滤ajax操作时
        	if("XMLHttpRequest".equalsIgnoreCase(xmlhttprequest)) {
        		httpResponse.setCharacterEncoding("UTF-8");
        		httpResponse.setContentType("text/json; charset=utf-8");    
        		httpResponse.setDateHeader("Expires", 0);
                PrintWriter out = httpResponse.getWriter();
                out.println("{\"resultCode\": -911, \"resultMessage\": \""+ ErrorMsg.PARAMETER_XSS_SECURITY +"\"}");
                out.flush();
                out.close();
                return;
        	}
        	
        }
        logger.debug("***** 请求合法。");
        chain.doFilter(xssRequest, response);
	}

	/**
	 * 初始化操作
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("XSSSecurityFilter init() begin");
		XSSSecurityManager.init(filterConfig);
		logger.info("XSSSecurityFilter init() end");
	}

	/**
     * 判断Request ,Response 类型
     * @param request
     *            ServletRequest
     * @param response
     *            ServletResponse
     * @throws ServletException 
     */
    private void checkRequestResponse(ServletRequest request,
            ServletResponse response) throws ServletException {
        if (!(request instanceof HttpServletRequest)) {
            throw new ServletException("Can only process HttpServletRequest");

        }
        if (!(response instanceof HttpServletResponse)) {
            throw new ServletException("Can only process HttpServletResponse");
        }
    }
}

