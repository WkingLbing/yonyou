/**********************************************************************
* $Id: UserAuthoInterceptor.java UserAuthInterceptor ,v0.1 2016年7月13日 下午5:29:05 DuanMinglei Exp $
* Copyright ©2016 yonyou . All rights reserved
***********************************************************************/

package com.yonyou.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 功能说明： 创建者：段明磊 E-mail: duanml1@yonyou.com 创建时间：2016年7月13日
 * 
 * <pre>
* 修改时间:       修改者:            
* 修改内容：
 * </pre>
 * 
 * 版本：0.1
 */
public class WebInterceptor implements HandlerInterceptor {

//	@Autowired
//	private UserService userService;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String url = request.getRequestURI();
		
		String token=request.getParameter("token");

		// System.out.println("***************************************************************************************************************************************");
		// System.out.println(url);
		//System.out.println("接收到请求url--------->" + url);
		// System.out.println("***************************************************************************************************************************************");
		// commonService.addApiStatistics(url);
		// logger.info("url====>"+url);

		// api接口调试相关地址
		if (url.indexOf("swagger-ui") > -1 || url.equals("/error") || url.indexOf("/swagger-resources") > -1
				|| url.indexOf(".js") > -1 || url.equals("/v2/api-docs") || url.equals("/help")) {
			return true;
		}

		if (url.equals("/user/login") || url.equals("/api/call")) {
			return true;
		}

		// 进行token认证
		//return this.checkToken(request, response);
		return true;
	}

//	private boolean checkToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		String token = request.getParameter("token");
//		//System.out.println("token----->" + token);
//		if (token == null || "".equals(token)) {
//
//			byte[] bytes = (JSON.toJSONString(ErrCode.ACCESS_TOKEN_INVALID)).getBytes();
//			response.setContentType("text/html;charset=UTF-8");
//			response.setContentLength(bytes.length);
//			response.getOutputStream().write(bytes);
//			response.getOutputStream().close();
//
//			return false;
//
//		}
//		UserDTO userDTO = userService.get(token);
//		if (userDTO == null) {
//			byte[] bytes = (JSON.toJSONString(ErrCode.ACCESS_TOKEN_INVALID)).getBytes();
//			response.setContentType("text/html;charset=UTF-8");
//			response.setContentLength(bytes.length);
//			response.getOutputStream().write(bytes);
//			response.getOutputStream().close();
//			return false;
//		} else {
//			return true;
//		}
//	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
