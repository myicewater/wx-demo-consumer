package com.link.service.activity;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.link.dao.activity.AwardMapper;
import com.link.dao.activity.DrawAwardInfoMapper;
import com.link.dao.activity.ShareInfoMapper;
import com.link.model.activity.Award;
import com.link.model.activity.DrawAwardInfo;
import com.link.model.activity.ShareInfo;

@Service
@Transactional
public class ActivityService {
	
	@Autowired
	private ShareInfoMapper shareInfoMapper;
	
	@Autowired
	private DrawAwardInfoMapper drawAwardInfoMapper;
	
	@Autowired
	private AwardMapper awardMapper;

	public int addShareRecord(ShareInfo info) {
		// TODO Auto-generated method stub
		return shareInfoMapper.insert(info);
	}

	public int getDrawTimes(Map map) {
		// TODO Auto-generated method stub
		return drawAwardInfoMapper.getDrawTimes( map);
	}

	public int getHitTimes(Map map) {
		// TODO Auto-generated method stub
		return drawAwardInfoMapper.getHitTimes( map);
	}

	public int addDrawInfo(DrawAwardInfo info) {
		// TODO Auto-generated method stub
		return drawAwardInfoMapper.insert(info);
	}

	public int getLevelDrawTimes(Map map) {
		// TODO Auto-generated method stub
		return drawAwardInfoMapper.getLevelDrawTimes( map);
	}

	public Award getAwardByLevel(Map map) {
		// TODO Auto-generated method stub
		return awardMapper.getAwardByLevel( map);
	}

	public int getHitUnGetTimes(Map map) {
		// TODO Auto-generated method stub
		return drawAwardInfoMapper.getHitUnGetTimes( map) ;
	}

	public int getAward(Map map) {
		// TODO Auto-generated method stub
		return drawAwardInfoMapper.getAward( map) ;
	}

	public int getShareTime(String openId) {
		// TODO Auto-generated method stub
		return shareInfoMapper.getShareTime( openId);
	}

	public DrawAwardInfo getHitUnGet(Map map) {
		// TODO Auto-generated method stub
		return drawAwardInfoMapper.getHitUnGet( map);
	}

	public DrawAwardInfo getHitAward(Map map) {
		// TODO Auto-generated method stub
		return drawAwardInfoMapper.getHitAward( map);
	}

	public String getBoxType(Map map) {
		// TODO Auto-generated method stub
		return shareInfoMapper.getBoxType( map) ;
	}

	



	
	
	
	
}
