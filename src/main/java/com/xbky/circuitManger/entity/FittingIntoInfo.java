package com.xbky.circuitManger.entity;

import java.util.Date;

public class FittingIntoInfo {
    /**
     *  唯一主键
     */
    private Long id;

    private String fittingNo;

    private String fittingName;

    private String fittingModel;

    private String unit;

    private Date createTime;
    private Date updateTime;

    public FittingIntoInfo(){

    }

    public FittingIntoInfo(Long id,String fittingNo, String fittingName, String fittingModel, String unit) {
        this.id = id;
        this.fittingNo = fittingNo;
        this.fittingName = fittingName;
        this.fittingModel = fittingModel;
        this.unit = unit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFittingModel() {
        return fittingModel;
    }

    public void setFittingModel(String fittingModel) {
        this.fittingModel = fittingModel;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
