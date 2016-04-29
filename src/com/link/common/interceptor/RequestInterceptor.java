package com.link.common.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.link.common.util.WeiXinJsUtil;

public class RequestInterceptor extends HandlerInterceptorAdapter{

	private static Logger logger = Logger.getLogger(RequestInterceptor.class);
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		WeiXinJsUtil.fetchConfig(request);
		String requestURL = request.getRequestURL().toString();
		if( requestURL.contains("/insurance/toInsuranceOrder.htm") 
				||requestURL.contains("/insurance/toTestPage.htm") 
				||  requestURL.contains("/userinfo/expandUser.htm") 
				|| requestURL.contains("/userinfo/expandUserInterface.htm")
				|| requestURL.contains("/product") 
				|| requestURL.contains("/activity") 
				|| requestURL.contains("/weixin/weixinFilter")
				|| requestURL.contains("/login/unbound.htm") 
				|| requestURL.contains("/login/login.htm") 
				||requestURL.contains("/weixin.htm")
				|| requestURL.contains("/login/doLogin.htm") 
				|| requestURL.contains("/login/doRegedit.htm") 
				|| requestURL.contains("/login/updatePwd.htm")
				|| requestURL.contains("/login/forget.htm")
				|| requestURL.contains("/login/forget.htm")
				|| requestURL.contains("/weixin/loadWeixinMenue.htm")
				|| requestURL.contains("/wllcmember/myFinancialPlanners.htm")
				|| requestURL.contains("/wllcmember/morePlannersNoLog.htm")
				|| requestURL.contains("/userinfo/getMessages.htm")
				|| requestURL.contains("/userinfo/expandUserShare.htm")
				
				){
			logger.info("请求被允许，访问在允许列表"+requestURL);
			//request.setAttribute("aaaaaaa", "bbbbbbbbb");
			
			return super.preHandle(request, response, handler);
		}
		
		String userId = request.getParameter("userId");
		//String openId = request.getParameter("openId");
		
		String sessionUserId = (String)request.getSession().getAttribute("userId");
		//String sessionOpenId = (String)request.getSession().getAttribute("openId");
		
		if(sessionUserId == null ){
			RequestDispatcher ds = request.getRequestDispatcher("/login/unbound.htm");
			ds.forward(request, response);
			logger.info("请求被拒绝，session为空"+requestURL);
			return false;
		}
		
		/*if(openId != null && sessionOpenId!= null){
			if(!openId.equals(sessionOpenId)){
				logger.info("openId:"+openId+" sessionOpenId: "+sessionOpenId);
				//request.getRequestDispatcher("/login/unbound.htm").forward(request, response).forward(request, response);
				request.getRequestDispatcher("/login/unbound.htm").forward(request, response);
				logger.info("请求被拒绝，openId 不匹配"+requestURL);
				return false;
				
			}
		}*/
		
		if(userId != null && sessionUserId!= null){
			if(!userId.equals(sessionUserId)){
				logger.info("userId:"+userId+" sessionUserId: "+sessionUserId);
				//request.getRequestDispatcher("/login/unbound.htm").forward(request, response).forward(request, response);
				request.getRequestDispatcher("/login/unbound.htm").forward(request, response);
				logger.info("请求被拒绝，userId 不匹配"+requestURL);
				return false;
				
			}
		}
		
		
		
		logger.info("请求被允许，权限校验通过"+requestURL);
		return super.preHandle(request, response, handler);
	}
	
	
}
