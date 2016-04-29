package com.link.common.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.link.common.util.RequestUtil;
import com.link.common.util.WeiXinJsUtil;

public class LoggerInterceptor extends HandlerInterceptorAdapter {
	static Logger logger = Logger.getLogger(LoggerInterceptor.class) ;
	static{
		BasicConfigurator.configure() ;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception{
		WeiXinJsUtil.fetchConfig(request);
		logger.info("请求方法为：" + request.getRequestURI());
		if(RequestUtil.getAllParam(request).length() < 250){
			logger.info("请求参数为：" + RequestUtil.getAllParam(request));
		}
		
		
		
		
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView) throws Exception{
		//logger.info("--------请求后") ;
		super.postHandle(request, response, handler, modelAndView);
 	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws Exception{
		//logger.info("-------显示视图后被调用") ;
		super.afterCompletion(request, response, handler, ex) ;
	}
}
