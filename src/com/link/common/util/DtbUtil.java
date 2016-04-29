package com.link.common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.link.weixin.util.HttpRequestUtil;
import com.link.weixin.util.MD5Util;

public class DtbUtil {

	private static Logger logger = Logger.getLogger(DtbUtil.class);
	
	/**
	 * 
	 * @param userId
	 * @param orderNo
	 * @param prdId
	 * @param map 接口独特参数
	 * @return
	 */
	public static JSONObject dtbVist(String prdId,Map map,String serviceName){
		
		String bizData = JSONObject.fromObject(map).toString();
		logger.info("DTB parmater:"+bizData);
		String timestamp = DateUtil.getCurrentTimeFormated("yyyyMMddHHmmss");
		String appSecret = MD5Util.MD5Encode(PropUtil.getValue("dtbAppSecret"));
		String dtbUrl = PropUtil.getValue("dtbUrl");
		String dtbAppKey = PropUtil.getValue("dtbAppKey");
		String str = HttpRequestUtil.sendPost(dtbUrl, "appKey="+dtbAppKey+"&serviceName="+serviceName+"&productID="+prdId+"&timestamp="+timestamp+"&dataFormat=json&charset=utf-8&sign="+appSecret+"&signType=md5&bizData="+bizData);
		logger.info("result:"+str);
		JSONObject jb = JSONObject.fromObject(str);
		return jb;
	}
	
	public static String getProductDtailUrl(String prdId,Map map,String serviceName){
		
		String bizData = JSONObject.fromObject(map).toString();
		logger.info("DTB parmater:"+bizData);
		String timestamp = DateUtil.getCurrentTimeFormated("yyyyMMddHHmmss");
		String appSecret = MD5Util.MD5Encode(PropUtil.getValue("dtbAppSecret"));
		String dtbUrl = PropUtil.getValue("dtbUrl");
		String dtbAppKey = PropUtil.getValue("dtbAppKey");
		String str = HttpRequestUtil.sendPost(dtbUrl, "appKey="+dtbAppKey+"&serviceName="+serviceName+"&productID="+prdId+"&timestamp="+timestamp+"&dataFormat=json&charset=utf-8&sign="+appSecret+"&signType=md5&bizData="+bizData);
		logger.info("result:"+str);
		return str;
	}
	
	public static void main(String[] args) {
		Map map = new HashMap();
		 map.put("orderID", "144048467577228013");
	       map.put("policyID", "10571001900120619042");
	       map.put("id", "13569");
	       map.put("developerID", "1234567890");
	       JSONObject jbb = dtbVist( "1", map,"cancelPolicy");
	       
	       String result = jbb.getString("bizData");
	       result = result.replaceAll("\\\"", "\"");
	       logger.info("interface result:"+result);
	       JSONObject jb = JSONObject.fromObject(result);
	       String errorCode = jb.getString("errorCode");
	       System.out.println(errorCode);
	}
}
