package com.link.weixin.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.link.common.util.PropUtil;
import com.link.model.weixin.TWeixinInfo;
import com.link.service.weixin.WeixinInfoService;
import com.link.weixin.pojo.Fans;
import com.link.weixin.resp.TextMessage;
import com.link.weixin.thread.TokenThread;
import com.link.weixin.util.MessageUtil;
import com.link.weixin.util.SpringBeanUtil;
import com.link.weixin.util.WeixinUtil;
/**
 * 
 * @author 朱素海
 * 
 * 包括几乎所有的微信事件处理
 *
 */
public class CoreService {
	private static String path = PropUtil.getValue("weixinServerAddr");
	private static final Logger logger = Logger.getLogger(CoreService.class);
	/**
	 * 处理微信发来的请求
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request) {
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型，event
			String msgType = requestMap.get("MsgType");
			
			
			if(MessageUtil.isEvent( msgType)){//事件请求
				
				String event = requestMap.get("Event");
				
				if(event.equals(MessageUtil.EVENT_TYPE_SCAN)){//已关注扫描事件,场景二维码扫描
					
					String sceneId = requestMap.get("EventKey");
					
					
					logger.info("进入已关注扫描事件，openid为：" + fromUserName+"场景Id："+sceneId);
					
					if("7758".equals(sceneId)){
						TextMessage tm = new TextMessage();
						tm.setToUserName(fromUserName);
						tm.setFromUserName(toUserName);
						tm.setCreateTime(new Date().getTime());
						tm.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
						
						tm.setContent("<a href='"+path+"exampath"+fromUserName+"'>点击这里</a>");
						return MessageUtil.textMessageToXml(tm);
					}
					
					return "";
				}
				
				
				/**
				 * 关注事件（包括扫描场景二维码的关注）
				 */
				if (MessageUtil.isSubscribe(event)) {
					
					String eventKey = requestMap.get("EventKey");
					
					if(eventKey != null && eventKey.startsWith("qrscene_")){
						logger.info("进入扫描场景二维码关注事件");
						String sceneId = requestMap.get("EventKey");
						sceneId = sceneId.substring(8, sceneId.length());
						
						
						
						//添加到数据库，如果存在不插入数据库
						Fans fans = WeixinUtil.getFansInfo(TokenThread.accessToken.getToken(), fromUserName);
						//保存关注着到数据库中
						WeixinInfoService weixinInfoService = (WeixinInfoService) SpringBeanUtil.getInstance().getBeanById("weixinInfoService");
						TWeixinInfo oldWeixinInfo =  weixinInfoService.getWeixinInfoByOpenId(fromUserName);
						if(oldWeixinInfo!=null&&!"".equals(oldWeixinInfo)){
							TWeixinInfo weixinInfo = new TWeixinInfo();
							weixinInfo.setOpenId(fromUserName);//微信号
							weixinInfo.setIsConnected("T");//是否取消关注（T：关注；F取消关注）
							Date date = new Date();
							weixinInfo.setUpdateTime(date);
							weixinInfo.setWxImgUrl(fans.getHeadimgurl());
							weixinInfo.setNickName(fans.getNickName());
							weixinInfo.setSex(fans.getSex().toString());
							weixinInfo.setCity(fans.getCity());
							weixinInfo.setCountry(fans.getCountry());
							weixinInfo.setProvince(fans.getProvince());
							weixinInfo.setLanguage(fans.getLanguage());
							weixinInfoService.update(weixinInfo);//更新
						}else{
							TWeixinInfo weixinInfo = new TWeixinInfo();
							weixinInfo.setOpenId(fromUserName);//微信号
							Date date = new Date();
							weixinInfo.setCreateTime(date);//创建时间
							weixinInfo.setIsConnected("T");//是否取消关注（T：关注；F取消关注）
							weixinInfo.setWxImgUrl(fans.getHeadimgurl());
							weixinInfo.setNickName(fans.getNickName());
							weixinInfo.setSex(fans.getSex().toString());
							weixinInfo.setCity(fans.getCity());
							weixinInfo.setCountry(fans.getCountry());
							weixinInfo.setProvince(fans.getProvince());
							weixinInfo.setLanguage(fans.getLanguage());
							weixinInfoService.save(weixinInfo);//保存
						}
						
						if("7758".equals(sceneId)){//场景扫描处理事件
							
							WeixinUtil.sendText(TokenThread.accessToken.getToken(), fromUserName, "欢迎关注三里屯太古里官方微信，请点击底部“领取礼盒”按钮，开启音乐之旅！点击“打开礼盒”领取神秘大礼哦！");
							
							TextMessage tm = new TextMessage();
							tm.setToUserName(fromUserName);
							tm.setFromUserName(toUserName);
							tm.setCreateTime(new Date().getTime());
							tm.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
							
							tm.setContent("<a href='"+path+"examPath?openId="+fromUserName+"'>点击这里</a>领取奖品/礼物");
							return MessageUtil.textMessageToXml(tm);
						}
						
					}else{
						logger.info("单纯的关注事件");
						
						//添加到数据库，如果存在不插入数据库
						Fans fans = WeixinUtil.getFansInfo(TokenThread.accessToken.getToken(), fromUserName);
						//保存关注着到数据库中
						WeixinInfoService weixinInfoService = (WeixinInfoService) SpringBeanUtil.getInstance().getBeanById("weixinInfoService");
						TWeixinInfo list = weixinInfoService.getWeixinInfoByOpenId(fromUserName);
						if(list!=null&&!"".equals(list)){
							TWeixinInfo weixinInfo = new TWeixinInfo();
							weixinInfo.setOpenId(fromUserName);//微信号
							weixinInfo.setIsConnected("T");//是否取消关注（T：关注；F取消关注）
							Date date = new Date();
							weixinInfo.setUpdateTime(date);
							weixinInfo.setWxImgUrl(fans.getHeadimgurl());
							weixinInfo.setNickName(fans.getNickName());
							weixinInfo.setSex(fans.getSex().toString());
							weixinInfo.setCity(fans.getCity());
							weixinInfo.setCountry(fans.getCountry());
							weixinInfo.setProvince(fans.getProvince());
							weixinInfo.setLanguage(fans.getLanguage());
							weixinInfoService.update(weixinInfo);//更新
						}else{
							TWeixinInfo weixinInfo = new TWeixinInfo();
							weixinInfo.setOpenId(fromUserName);//微信号
							Date date = new Date();
							weixinInfo.setCreateTime(date);//创建时间
							weixinInfo.setIsConnected("T");//是否取消关注（T：关注；F取消关注）
							weixinInfo.setWxImgUrl(fans.getHeadimgurl());
							weixinInfo.setNickName(fans.getNickName());
							weixinInfo.setSex(fans.getSex().toString());
							weixinInfo.setCity(fans.getCity());
							weixinInfo.setCountry(fans.getCountry());
							weixinInfo.setProvince(fans.getProvince());
							weixinInfo.setLanguage(fans.getLanguage());
							weixinInfoService.save(weixinInfo);//保存
						}
						
					}
					
					
					
					
					TextMessage textMessage = new TextMessage();
					textMessage.setToUserName(fromUserName);
					textMessage.setFromUserName(toUserName);
					textMessage.setCreateTime(new Date().getTime());
					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					String content = "";
					content += "欢迎关注";
					
					textMessage.setContent(content);
					
					return MessageUtil.textMessageToXml(textMessage);
				}
				
				
				/**
				 * 取消关注
				 */
				if (MessageUtil.isUnsubscribe(event)) {
					//保存关注着到数据库中
					WeixinInfoService weixinInfoService = (WeixinInfoService) SpringBeanUtil.getInstance().getBeanById("weixinInfoService");
					TWeixinInfo weixinInfo = new TWeixinInfo();
					weixinInfo.setOpenId(fromUserName);
					Date date = new Date();
					weixinInfo.setUpdateTime(date);
					weixinInfo.setUserId(null);//去除关系
					weixinInfo.setIsConnected("F");
					weixinInfoService.disConnect(weixinInfo);
					logger.info("取消关注，openid为：" + fromUserName);
				}
				
				
				/**
				 * 点击菜单事件
				 */
				if(MessageUtil.isClick(event)){
					
					String menueKey = requestMap.get("EventKey");
					logger.info("menueKey:"+menueKey);
					if("keyword1".equals(menueKey)){
						TextMessage tm = new TextMessage();
						tm.setToUserName(fromUserName);
						tm.setFromUserName(toUserName);
						tm.setCreateTime(new Date().getTime());
						tm.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
						
						tm.setContent("<a href='"+path+"examPath?openId="+fromUserName+"'>点击这里</a>");
						return MessageUtil.textMessageToXml(tm);
					}
					
					if("keyword2".equals(menueKey)){
						TextMessage tm = new TextMessage();
						tm.setToUserName(fromUserName);
						tm.setFromUserName(toUserName);
						tm.setCreateTime(new Date().getTime());
						tm.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
						
						tm.setContent("<a href='"+path+"examPath?openId="+fromUserName+"'>点击这里</a>");
						return MessageUtil.textMessageToXml(tm);
					}
				}
				
				
			}//事件请求结束
			
			
			/**
			 * 关键字事件处理
			 */
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				// 接收用户发送的文本消息内容
				String content = requestMap.get("Content");
				if("关键字".equals(content)){
					TextMessage tm = new TextMessage();
					tm.setToUserName(fromUserName);
					tm.setFromUserName(toUserName);
					tm.setCreateTime(new Date().getTime());
					tm.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					
					tm.setContent("回复内容");
					return MessageUtil.textMessageToXml(tm);
				}
			}
			
			
		} catch (Exception e) {
			logger.error("核心service异常", e);
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * emoji表情转换(hex -> utf-16)
	 * 
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}
	
	
}
