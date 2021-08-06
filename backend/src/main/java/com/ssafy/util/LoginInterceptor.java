package com.ssafy.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.sharehouse.model.service.UserService;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Autowired
	UserService mSer;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String servletPath = request.getServletPath();
		//System.out.println("interceptor........................");
		if(servletPath.endsWith("loginpage") || servletPath.endsWith("mem/regpage")
				|| servletPath.endsWith("/")
				|| servletPath.endsWith("mem/meminsert")
				|| servletPath.endsWith("mem/memlogin")
				|| servletPath.contains("/api")) {
			return true;
		}
		
		//System.out.println(servletPath);
		
		if(request.getSession().getAttribute("currentId")==null) {

			request.setAttribute("msg", "로그인 해주세요");
			request.getRequestDispatcher("/").forward(request, response);
			return false;
		}
		return true;
	}
	// controller의 handler가 끝나면 처리됨
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
//		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//		logger.debug("postHandle");
//		logger.error("postHandle...............................");
//		System.out.println("postHandle...............................");
	}
	// view까지 처리가 끝난 후에 처리됨
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
//		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//		logger.error("afterCompletion...............................");
//		System.out.println("afterCompletion...............................");
	}
}
