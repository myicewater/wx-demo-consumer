package com.link.model.activity;

import java.util.Date;

public class DrawAwardInfo {
    private Integer id;

    private String openId;

    private Integer awardLevel;

    private Date creatTime;

    private String isGeted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getAwardLevel() {
        return awardLevel;
    }

    public void setAwardLevel(Integer awardLevel) {
        this.awardLevel = awardLevel;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getIsGeted() {
        return isGeted;
    }

    public void setIsGeted(String isGeted) {
        this.isGeted = isGeted;
    }
}