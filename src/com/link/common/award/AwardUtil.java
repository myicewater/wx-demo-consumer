package com.link.common.award;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.link.common.util.PropUtil;
import com.link.controller.award.AwardController;

public class AwardUtil {
	
	private static final Logger logger = Logger.getLogger(AwardUtil.class);

	public static Section award(){
		String awardLevel = PropUtil.getValue("awardLevel");
		String tatalNumber = PropUtil.getValue("tatalNumber");
		
		List<Section> sections = new ArrayList<Section>();
		String temp =null;
		String end = "";
		Section section = null;
		for(int i=0;i<Integer.valueOf(awardLevel);i++){
			temp = PropUtil.getValue("awardLevel"+(i+1));
			logger.info("temp:"+temp);
			String sec[] = temp.split(",");
			section = new Section(i+1,Integer.valueOf(sec[0]),Integer.valueOf(sec[1]));
			sections.add(section);
		}
		
		Random random  = new Random();
		int rand = random.nextInt(Integer.valueOf(tatalNumber));
		logger.info("rand:"+rand);
		for(Section s:sections){
			if(rand >= s.getStart() && rand <= s.getEnd()){
				logger.info("hit "+s.getLevel()+" level");
				return s;
			}
		}
		return null;
		
	}
	
	
}
