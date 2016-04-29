package com.link.model.activity;

import java.util.Date;

public class Award {
    private Integer id;

    private Integer awardLevel;

    private Integer taltolCount;

    private Integer leftCount;

    private Date creatTime;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAwardLevel() {
        return awardLevel;
    }

    public void setAwardLevel(Integer awardLevel) {
        this.awardLevel = awardLevel;
    }

    public Integer getTaltolCount() {
        return taltolCount;
    }

    public void setTaltolCount(Integer taltolCount) {
        this.taltolCount = taltolCount;
    }

    public Integer getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(Integer leftCount) {
        this.leftCount = leftCount;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}