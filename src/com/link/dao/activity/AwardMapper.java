package com.link.dao.activity;

import java.util.Map;

import com.link.model.activity.Award;

public interface AwardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Award record);

    int insertSelective(Award record);

    Award selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Award record);

    int updateByPrimaryKey(Award record);

	Award getAwardByLevel(Map map);
}