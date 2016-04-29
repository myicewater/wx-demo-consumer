package com.link.dao.activity;

import java.util.Map;

import com.link.model.activity.DrawAwardInfo;

public interface DrawAwardInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DrawAwardInfo record);

    int insertSelective(DrawAwardInfo record);

    DrawAwardInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DrawAwardInfo record);

    int updateByPrimaryKey(DrawAwardInfo record);

	int getDrawTimes(Map map);

	int getHitTimes(Map map);

	int getLevelDrawTimes(Map map);

	int getHitUnGetTimes(Map map);

	int getAward(Map map);

	DrawAwardInfo getHitUnGet(Map map);

	DrawAwardInfo getHitAward(Map map);
}