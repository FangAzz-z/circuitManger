package com.xbky.circuitManger.entity;

import java.util.Date;

public class CheckMaintainRecord {
    private Long id;
    private String maintainId;
    private String receiveDate;
    private String completeDate;
    private String maintainCardNo;
    private String maintainCardModel;
    private String maintainCardCategory;
    private String maintainCardBrand;
    private String maintainUser;
    private String wxStatus = "";
    private String wxShow = "";
    private String wxMethod = "";
    private String wxResult = "";
    private String maintainDesc;
    private String maintainFitting;
    private Date createTime;
    private Date updateTime;

    private String receiveStartTime;
    private String receiveEndTime;
    private String completeStartTime;
    private String completeEndTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaintainId() {
        return maintainId;
    }

    public void setMaintainId(String maintainId) {
        this.maintainId = maintainId;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public String getMaintainCardNo() {
        return maintainCardNo;
    }

    public void setMaintainCardNo(String maintainCardNo) {
        this.maintainCardNo = maintainCardNo;
    }

    public String getMaintainCardModel() {
        return maintainCardModel;
    }

    public void setMaintainCardModel(String maintainCardModel) {
        this.maintainCardModel = maintainCardModel;
    }

    public String getMaintainCardCategory() {
        return maintainCardCategory;
    }

    public void setMaintainCardCategory(String maintainCardCategory) {
        this.maintainCardCategory = maintainCardCategory;
    }

    public String getMaintainCardBrand() {
        return maintainCardBrand;
    }

    public void setMaintainCardBrand(String maintainCardBrand) {
        this.maintainCardBrand = maintainCardBrand;
    }

    public String getMaintainUser() {
        return maintainUser;
    }

    public void setMaintainUser(String maintainUser) {
        this.maintainUser = maintainUser;
    }

    public String getMaintainDesc() {
        return maintainDesc;
    }

    public void setMaintainDesc(String maintainDesc) {
        this.maintainDesc = maintainDesc;
    }

    public String getMaintainFitting() {
        return maintainFitting;
    }

    public void setMaintainFitting(String maintainFitting) {
        this.maintainFitting = maintainFitting;
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

    public String getWxStatus() {
        return wxStatus;
    }

    public void setWxStatus(String wxStatus) {
        this.wxStatus = wxStatus;
    }

    public String getWxShow() {
        return wxShow;
    }

    public void setWxShow(String wxShow) {
        this.wxShow = wxShow;
    }

    public String getWxMethod() {
        return wxMethod;
    }

    public void setWxMethod(String wxMethod) {
        this.wxMethod = wxMethod;
    }

    public String getWxResult() {
        return wxResult;
    }

    public void setWxResult(String wxResult) {
        this.wxResult = wxResult;
    }

    public String getReceiveStartTime() {
        return receiveStartTime;
    }

    public void setReceiveStartTime(String receiveStartTime) {
        this.receiveStartTime = receiveStartTime;
    }

    public String getReceiveEndTime() {
        return receiveEndTime;
    }

    public void setReceiveEndTime(String receiveEndTime) {
        this.receiveEndTime = receiveEndTime;
    }

    public String getCompleteStartTime() {
        return completeStartTime;
    }

    public void setCompleteStartTime(String completeStartTime) {
        this.completeStartTime = completeStartTime;
    }

    public String getCompleteEndTime() {
        return completeEndTime;
    }

    public void setCompleteEndTime(String completeEndTime) {
        this.completeEndTime = completeEndTime;
    }

}
