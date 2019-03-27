package com.yonyou.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
public class WebInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String url = request.getRequestURI();
		
		
		if (url.indexOf("swagger-ui") > -1 || url.equals("/error") || url.indexOf("/swagger-resources") > -1
				|| url.indexOf(".js") > -1 || url.equals("/v2/api-docs") || url.equals("/help")) {
			return true;
		}

		if (url.equals("/user/login") || url.equals("/api/call")) {
			return true;
		}

		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
