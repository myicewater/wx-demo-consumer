package com.link.controller.activity;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.link.common.util.PropUtil;
import com.link.model.activity.DrawAwardInfo;
import com.link.model.activity.ShareInfo;
import com.link.service.activity.ActivityService;
import com.link.weixin.pojo.AccessToken;
import com.link.weixin.thread.TokenThread;
import com.link.weixin.util.QRCodeUtil;


@Controller
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;

	
	

	
	private static final Logger logger = Logger.getLogger(ActivityController.class);
	
	
	
	/**
	   * 领取礼盒
	   * @param request
	   * @param model
	   * @return
	   */
	  @RequestMapping(value="/music/fetchGift.htm")
	  public String  fetchGift(HttpServletRequest request,Model model){
	   
		  
	      String openId =  request.getParameter("openId");
	      request.setAttribute("openId", openId);
	      return "/music/H5.html";
	  }
	  
	  
	  
	  /**
	   * 查看奖品
	   * @param request
	   * @param model
	   * @return
	   */
	  @RequestMapping(value="/music/checkAward.htm")
	  public String  checkAward(HttpServletRequest request,Model model){
	   
		  
	      String openId =  request.getParameter("openId");
	      Map map = new HashMap();
	      
	      map.put("openId", openId);
	      
	      
	      DrawAwardInfo info = activityService.getHitAward(map);
	      if(info == null){
	    	  request.setAttribute("code", 0);
	      }else{
	    	  request.setAttribute("code", info.getAwardLevel());
	      }
	      
	      request.setAttribute("openId", openId);
	      return "/music/check-prize.html";
	  }
	  
	  
	  /**
	   * 领取奖品
	   * @param request
	   * @param model
	   * @return
	   */
	  @RequestMapping(value="/music/getGift.htm")
	  public String  getGift(HttpServletRequest request,Model model){
	   
		  
	      String openId =  request.getParameter("openId");
	      
	      Map map = new HashMap();
	      
	      map.put("openId", openId);
	      
	      
	      int hitCountUnGet = activityService.getHitUnGetTimes(map);
	      
	      int hitCount = activityService.getHitTimes(map);
	      if(hitCountUnGet > 0){
	    	  logger.info("有未领取的奖品");
	    	  DrawAwardInfo info = activityService.getHitUnGet(map);
	    	  request.setAttribute("level", info.getAwardLevel());
	    	  logger.info("有未领取的奖品，"+info.getAwardLevel()+"等奖");
	    	  request.setAttribute("code", "00");
	      }else if(hitCount > 0){
	    	  logger.info("奖品已经领取了");
	    	  request.setAttribute("code", "01");
	      }else{
	    	  logger.info("根本没有中奖");
	    	  request.setAttribute("code", "02");
	      }
	      
	      
	      
	      request.setAttribute("openId", openId);
	      return "/music/nowprize.html";
	  }
	  
	  
	  /**
	   * 添加分享记录
	   * @param request
	   * @param model
	   * @return
	   */
	  @RequestMapping(value="/music/getAward.htm")
	  @ResponseBody
	  public Map  getAward(HttpServletRequest request,Model model){
	      Map resultMap = new HashMap();
	   
	      String openId  =  request.getParameter("openId");
	      
	      Map map = new HashMap();
	      map.put("openId", openId);
	      
	      int result = activityService.getAward(map);
	      if(result>0){
	        resultMap.put("resultCode", "00");
	       }else{
	        resultMap.put("resultCode", "99");
	       }

	      
	      return resultMap;
	  }
	  
	  
	  /**
	   * 添加分享记录
	   * @param request
	   * @param model
	   * @return
	   */
	  @RequestMapping(value="/music/addShareRecord.htm")
	  @ResponseBody
	  public Map  addShareRecord(HttpServletRequest request,Model model){
	      Map resultMap = new HashMap();
	   
	      String openId  =  request.getParameter("openId");
	      String boxType= request.getParameter("boxType");
	      ShareInfo info = new ShareInfo();
	      info.setCreateTime(new Date());
	      info.setIsUsed("0");
	      info.setOpenId(openId);
	      info.setBoxType(boxType);
	      int result = activityService.addShareRecord(info);
	      if(result>0){
	        resultMap.put("resultCode", "00");
	       }else{
	        resultMap.put("resultCode", "99");
	       }

	      
	      return resultMap;
	  }
	  
	  
	  /**
	   * 
	   * @param request
	   * @param model
	   * @return
	   */
	  @RequestMapping(value="/music/openGift.htm")
	  public String  openGift(HttpServletRequest request,Model model){
	   
	   
	      String openId =  request.getParameter("openId");
	      
	      Map map = new HashMap();
	      map.put("openId", openId);
	      String boxType = activityService.getBoxType(map);
	      request.setAttribute("boxType", boxType);
	      int  shareTime =activityService.getShareTime(openId);
	      if(shareTime == 0){
	    	  logger.info("还没有分享过");
	    	  request.setAttribute("leftTimes", "99");
	      }else{
	    	  int drawTimes = activityService.getDrawTimes(map);
	    	  String drawAwardTimes = PropUtil.getValue("drawAwardTimes");
	    	  int leftTimes = Integer.valueOf(drawAwardTimes)- drawTimes;
	    	  logger.info("剩余抽奖次数："+leftTimes);
	    	  
	    	  request.setAttribute("leftTimes",leftTimes);
	      }
	      
	      request.setAttribute("openId", openId);
	      return "/music/prize.html";
	  }
	
	
	/**
	 * 永久二维码例子
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/activity/qr.htm")
	public String qr(HttpServletRequest request,Model model){
		
		
			AccessToken accessToken = TokenThread.accessToken;
			String accessTokenStr = accessToken.getToken();
			logger.info("accessTokenStr:"+accessTokenStr);
			String ticket = QRCodeUtil.createQRCode(accessTokenStr, Integer.valueOf("7758"));
			logger.info("ticket:"+ticket);
			String qrsavepath = PropUtil.getValue("qrsavepath");
			String savePath = request.getSession().getServletContext().getRealPath(qrsavepath);
			
			logger.info("savePath:"+savePath);
			
			String targetDir = "";
			if(!savePath.endsWith("/")){
				targetDir += savePath+ File.separator  +"check_hit.jpg";
			}else{
				targetDir +=  savePath+ "check_hit.jpg";
			}
			
			logger.info("picture directory:"+savePath);
			
			File savePathfile  = new File(savePath);
			if(!savePathfile.exists()){
				savePathfile.mkdir();
			}
			

			File targetFile = new File(targetDir);
			
			
			if(!targetFile.exists()){
				
				QRCodeUtil.getQRCode(ticket, targetDir);
			}
			
			String relativePath = "";
			if(qrsavepath.endsWith("/")){
				relativePath =qrsavepath +"check_hit.jpg";
			}else{
				relativePath =qrsavepath+"/" +"check_hit.jpg";
			}
			request.setAttribute("relativePath", relativePath);
			
	
			String weixinServerAddr = PropUtil.getValue("weixinServerAddr");
			request.setAttribute("weixinServerAddr", weixinServerAddr);
		
		

			return "/music/qr.html";
		
		}
	
	
	/**
	 * 临时二维码例子
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/activity/temporaryQr.htm")
	public String temporaryQr(HttpServletRequest request,Model model){
		
		
			AccessToken accessToken = TokenThread.accessToken;
			String accessTokenStr = accessToken.getToken();
			logger.info("accessTokenStr:"+accessTokenStr);
			String ticket = QRCodeUtil.createQRCodeTemporary(accessTokenStr, 8899,120);
			logger.info("ticket:"+ticket);
			String qrsavepath = PropUtil.getValue("qrsavepath");
			String savePath = request.getSession().getServletContext().getRealPath(qrsavepath);
			
			logger.info("savePath:"+savePath);
			
			String targetDir = "";
			if(!savePath.endsWith("/")){
				targetDir += savePath+ File.separator  +"check_hit.jpg";
			}else{
				targetDir +=  savePath+ "check_hit.jpg";
			}
			
			logger.info("picture directory:"+savePath);
			
			File savePathfile  = new File(savePath);
			if(!savePathfile.exists()){
				savePathfile.mkdir();
			}
			

			File targetFile = new File(targetDir);
			
			
			if(!targetFile.exists()){
				
				QRCodeUtil.getQRCode(ticket, targetDir);
			}
			
			String relativePath = "";
			if(qrsavepath.endsWith("/")){
				relativePath =qrsavepath +"check_hit.jpg";
			}else{
				relativePath =qrsavepath+"/" +"check_hit.jpg";
			}
			request.setAttribute("relativePath", relativePath);
			
	
			String weixinServerAddr = PropUtil.getValue("weixinServerAddr");
			request.setAttribute("weixinServerAddr", weixinServerAddr);
		
		

			return "/music/qr.html";
		
		}
	
	
	
}
