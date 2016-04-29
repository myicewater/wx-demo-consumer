package com.link.weixin.util;

import org.apache.log4j.Logger;

public class LogUtil {
	/**
	 * 日志打印
	 */
	private static final Logger logger = Logger.getLogger(LogUtil.class);
	
	public static void printLog(String msg){
		logger.error(msg);
	}
	public static void printLog(String msg,Exception e){
		logger.error(msg, e);
	}
	public static void printInfoLog(String msg){
		logger.info(msg);
	}
}
