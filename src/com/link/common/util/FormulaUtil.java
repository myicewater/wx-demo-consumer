package com.link.common.util;

import java.math.BigDecimal;

/**
 * 公式工具类
 * @author Administrator
 *
 */
public class FormulaUtil {

	/**
	 * 计算预期收益
	 * 
	 * @param days
	 * @param profit
	 * @param buyAmount
	 * @return
	 */
	public static Double expectedEarnCount(int days,double profit,double buyAmount){
		Double result = days*profit*buyAmount/365/100;
		//System.out.println("result:"+result);
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(FormulaUtil.expectedEarnCount(180,11,1000));
	}
}
