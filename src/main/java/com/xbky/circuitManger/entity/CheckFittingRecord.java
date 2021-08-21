package com.xbky.circuitManger.entity;

import java.util.Date;

public class CheckFittingRecord {
    private Long id;
    private String fittingModel;
    private String fittingNum;
    private String fittingNo;
    private String fittingName;
    private Date createTime;
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFittingModel() {
        return fittingModel;
    }

    public void setFittingModel(String fittingModel) {
        this.fittingModel = fittingModel;
    }

    public String getFittingNum() {
        return fittingNum;
    }

    public void setFittingNum(String fittingNum) {
        this.fittingNum = fittingNum;
    }

    public String getFittingNo() {
        return fittingNo;
    }

    public void setFittingNo(String fittingNo) {
        this.fittingNo = fittingNo;
    }

    public String getFittingName() {
        return fittingName;
    }

    public void setFittingName(String fittingName) {
        this.fittingName = fittingName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
