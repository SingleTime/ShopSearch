/**
 * AuthorityInterceptor.java 
 * Copyright © 2009-2015, 杭州跃度网络科技有限公司
 * 
 * @author liao
 * @create 2015年2月3日
 */
package com.chuck.shop.framework.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

 


/**
 * 
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger.getLogger(AuthorityInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.debug("AuthorityInterceptor.preHandler start...");
		 
		logger.debug("AuthorityInterceptor.preHandler end...");
		return true;
	}

 
}
