package com.link.common.util;

public class MyUUID {
	
	private MyUUID(){}
	
	/**
	 * 获取32位UUID
	 * @return
	 */
	public static String get(){
		return java.util.UUID.randomUUID().toString().replaceAll("-", "");
	}
}
