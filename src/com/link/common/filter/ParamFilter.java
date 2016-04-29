package com.link.common.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
public class ParamFilter implements Filter{
	
	private static final Logger logger = Logger.getLogger(ParamFilter.class);
	
	
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
         
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
    	logger.info("进入userId 转 openId 系统。。。");
    	String userId = request.getParameter("userId");
    	
//    	String openId = request.getParameter("openId");
//    	if(openId == null){
//    		UserInfoService userInfoService = (UserInfoService) SpringBeanUtil.getInstance().getBeanById("userInfoService");
//        	if(userId != null){
//        		openId=userInfoService.getOpenIdByUserId(userId);
//        		logger.info("过滤器查询openId:"+openId);
//        	}
//    	}
    	
//        Map<String,String[]> m = new HashMap<String,String[]>(request.getParameterMap());  
//        m.put("openId", new String[]{openId});
//        request = new ParameterRequestWrapper((HttpServletRequest)request, m);  
           
        chain.doFilter(request, response); 
    }
 
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
         
    }
}
