package com.link.dao.weixin;

import com.link.model.weixin.TWeixinInfo;

public interface TWeixinInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TWeixinInfo record);

    int insertSelective(TWeixinInfo record);

    TWeixinInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TWeixinInfo record);

    int updateByPrimaryKey(TWeixinInfo record);

	TWeixinInfo getWeixinInfoByOpenId(String fromUserName);
}