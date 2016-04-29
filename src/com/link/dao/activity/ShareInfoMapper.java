package com.link.dao.activity;

import java.util.Map;

import com.link.model.activity.DrawAwardInfo;
import com.link.model.activity.ShareInfo;

public interface ShareInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShareInfo record);

    int insertSelective(ShareInfo record);

    ShareInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShareInfo record);

    int updateByPrimaryKey(ShareInfo record);

	int getShareTime(String openId);

	DrawAwardInfo getHitUnGet(Map map);

	String getBoxType(Map map);
}