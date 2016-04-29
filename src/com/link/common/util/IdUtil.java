package com.link.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdUtil {

	/**
	 * 生成订单Id
	 * @return
	 */
	public static String buildOrderId(String terminalType,Integer productType){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = formatter.format(new Date());
		//随机四位
		int randomFour = (int) (1000 + Math.random() * (9999 - 1000 + 1));
		
		return "LK"+date+terminalType+productType+randomFour;
	}
}
