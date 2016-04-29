package com.link.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成用户ID的公共方法
 * @author ChenWenchao
 *
 */
public class UserIdUtil {
	
	public static String createUserId(String regTerm) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = formatter.format(new Date());
		//公式：(数据类型)(最小值+Math.random()*(最大值-最小值+1))
		int fourNum = (int) (1000 + Math.random() * (9999 - 1000 + 1));
		String userId = date + regTerm + Integer.toString(fourNum);
		return userId;
		
	}

}
