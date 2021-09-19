package com.xbky.circuitManger.entity;

import java.util.Date;

public class CheckFittingRecord {
    private Long id;
    private String fittingModel;
    private Integer fittingNum;
    private String fittingNo;
    private String fittingName;
    private Integer lowLimit;
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

    public Integer getFittingNum() {
        return fittingNum;
    }

    public void setFittingNum(Integer fittingNum) {
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

    public Integer getLowLimit() {
        return lowLimit;
    }

    public void setLowLimit(Integer lowLimit) {
        this.lowLimit = lowLimit;
    }
}
