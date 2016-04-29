package com.link.controller.award;

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

import com.link.common.award.AwardUtil;
import com.link.common.award.Section;
import com.link.common.util.PropUtil;
import com.link.model.activity.Award;
import com.link.model.activity.DrawAwardInfo;
import com.link.service.activity.ActivityService;


@Controller
public class AwardController {
	
	@Autowired
	private ActivityService activityService;
	
	private static final Logger logger = Logger.getLogger(AwardController.class);
	
	/**
	   *
	   * @param request
	   * @param model
	   * @return
	   */
	  @RequestMapping(value="/award/award.htm")
	  @ResponseBody
	  public Map  award(HttpServletRequest request,Model model){
	      Map resultMap = new HashMap();
	   
	      String drawAwardTimes = PropUtil.getValue("drawAwardTimes");
	      int  times = Integer.valueOf(drawAwardTimes);
	      
	      String openId = request.getParameter("openId");
	      Map map = new HashMap();
	      map.put("openId", openId);
	      
	      int drawTimes = activityService.getDrawTimes(map);
	      if(drawTimes >= times){
	    	  logger.info("没有抽奖次数了");
	    	  resultMap.put("code", "99");
	    	  return resultMap;
	      }
	      
	      int hitTimes = activityService.getHitTimes(map);
	      if(hitTimes > 0){
	    	  logger.info("已经中过奖了");
	    	  DrawAwardInfo info = new DrawAwardInfo();
	    	  info.setAwardLevel(0);
	    	  info.setCreatTime(new Date());
	    	  info.setOpenId(openId);
	    	  info.setIsGeted("0");
	    	  int result =activityService.addDrawInfo(info);
	    	  resultMap.put("code", "99");
	    	  return resultMap;
	      }
	      
	      Section s = AwardUtil.award();
	      if(s == null){
	    	  logger.info("没有中奖");
	    	  DrawAwardInfo info = new DrawAwardInfo();
	    	  info.setAwardLevel(0);
	    	  info.setCreatTime(new Date());
	    	  info.setOpenId(openId);
	    	  info.setIsGeted("0");
	    	  int result =activityService.addDrawInfo(info);
	    	  
	    	  resultMap.put("code", "0");
	      }else{
	    	  int level = s.getLevel();
	    	  map.put("level", level);
	    	  int levelDrawTimes = activityService.getLevelDrawTimes(map);
	    	  
	    	  Award award = activityService.getAwardByLevel(map);
	    	  if(levelDrawTimes >= award.getTaltolCount()){
	    		  logger.info("抽中了"+level+"等奖，但是没有了");
	    		  DrawAwardInfo info = new DrawAwardInfo();
		    	  info.setAwardLevel(0);
		    	  info.setCreatTime(new Date());
		    	  info.setOpenId(openId);
		    	  info.setIsGeted("0");
		    	  int result =activityService.addDrawInfo(info);
		    	  
		    	  resultMap.put("code", "0");
		    	  return resultMap;
	    	  }
	    	  
	    	  
	    	  logger.info("抽中了"+level+"等奖");
	    	  DrawAwardInfo info = new DrawAwardInfo();
	    	  info.setAwardLevel(level);
	    	  info.setCreatTime(new Date());
	    	  info.setOpenId(openId);
	    	  info.setIsGeted("0");
	    	  int result =activityService.addDrawInfo(info);
	    	  
	    	  resultMap.put("code", s.getLevel());
	    	  return resultMap;
	      }

	      
	      
	      
	      
	      return resultMap;
	  }
	
	
	
	
}
