package com.link.service.weixin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.link.dao.weixin.TWeixinInfoMapper;
import com.link.model.weixin.TWeixinInfo;

@Service
@Transactional
public class WeixinInfoService {

	private static Logger logger = Logger.getLogger(WeixinInfoService.class);
	
	@Autowired
	private TWeixinInfoMapper tWeixinInfoMapper;

	public void update(TWeixinInfo weixinInfo) {
		// TODO Auto-generated method stub
		tWeixinInfoMapper.updateByPrimaryKeySelective(weixinInfo);
	}

	public void save(TWeixinInfo weixinInfo) {
		// TODO Auto-generated method stub
		tWeixinInfoMapper.insert(weixinInfo);
	}

	public TWeixinInfo getWeixinInfoByOpenId(String fromUserName) {
		// TODO Auto-generated method stub
		return tWeixinInfoMapper.getWeixinInfoByOpenId(fromUserName);
	}

	public void disConnect(TWeixinInfo weixinInfo) {
		// TODO Auto-generated method stub
		tWeixinInfoMapper.updateByPrimaryKeySelective(weixinInfo);
	}
}
