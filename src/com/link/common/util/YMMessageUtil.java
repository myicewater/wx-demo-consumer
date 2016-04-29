package com.link.common.util;

public class YMMessageUtil {
	private static YMMessageUtil message=new YMMessageUtil();
	private static String content="默认短信提示";
	private static final String SN="SDK-WSS-010-08636";
	private static final String PWD="2f961f0-";
	
	/**
	 * @author 胡萌
	 * result 0短信发送成功 
	 * @param 
	 * con 发送的内容
	 * mobile 手机号
	 * @return
	 * @throws java.lang.Exception 
	 */
	public static String sendMessage(String con,String mobile) throws java.lang.Exception{
		String result = "";
		if (con == null || con.trim().equals("")) {
			con = content;
		}

		if (message == null) {
			message = new YMMessageUtil();
		}

		String sn=SN;
		String pwd=PWD;
		if (sn == null || sn == pwd) {
			throw new Exception("配置文件未配置对应值");
		}

		if (mobile == null) {
			throw new Exception("手机号码不能为空");
		}

		result = new Client(SN, PWD).mt(mobile, con + "【邻客金融】", "", "", "");

		return result;
	}
	
	/*private static void getBalance(){
		String key=PropUtil.getValue("cms_key");
		String pwd=PropUtil.getValue("cms_pwd");
		String url="http://sdk4report.eucp.b2m.cn:8080/sdkproxy/querybalance.action?cdkey="+key+"&password="+pwd;
		
	}*/
}
