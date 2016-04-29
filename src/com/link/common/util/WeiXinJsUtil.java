package com.link.common.util;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.link.weixin.pojo.AccessToken;
import com.link.weixin.thread.TokenThread;
import com.link.weixin.util.CreatSignature;

public class WeiXinJsUtil {

	private static final Logger logger = LoggerFactory.getLogger(WeiXinJsUtil.class);
	public static void fetchConfig(HttpServletRequest request ){
		//配置微信js
		// 这部分是拦截sdk-js接口所需要的参数
		String appId = PropUtil.getValue("weixinAppid");
		Long datetime = new Date().getTime();
		String da = datetime.toString().substring(0, 9);
		Long time = Long.parseLong(da);
		String nonceStr = UUID.randomUUID().toString();
		
		String url = PropUtil.getValue("weixinServerAddr");
		logger.info("server url:"+url);
		String paramters = request.getQueryString();
		logger.info("query string:"+paramters);
		String URL = url;
		URL += request.getServletPath().substring(1);
		logger.info("servlet path:"+request.getServletPath().substring(1));
		if(paramters != null){
			URL +="?"+paramters;
		}
		
		AccessToken accessToken = TokenThread.accessToken;
		String accessTokenStr = accessToken.getToken();
		logger.info("jsapi_ticket:"+accessTokenStr);
		logger.info("nonceStr:"+nonceStr);
		logger.info("timestamp:"+time);
		logger.info("url:"+URL);
		String signature = CreatSignature.getSignature(accessTokenStr,time, nonceStr, URL);
		logger.info("signature:"+signature);
		
		String weixinServerAddr = PropUtil.getValue("weixinServerAddr");
		
		request.setAttribute("weixinServerAddr", weixinServerAddr);
		request.setAttribute("appId", appId);
		request.setAttribute("timestamp", time);
		request.setAttribute("nonceStr", nonceStr);
		request.setAttribute("signature", signature);
		
	}
}
