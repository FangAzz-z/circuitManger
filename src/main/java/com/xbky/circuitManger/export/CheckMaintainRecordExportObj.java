package com.xbky.circuitManger.export;

import com.xbky.circuitManger.annotation.Column;
import com.xbky.circuitManger.annotation.DateFormat;

import java.util.Date;
import java.util.LinkedHashMap;

public class CheckMaintainRecordExportObj {
    @Column(name="maintain_id")
    private String maintainId;

    @Column(name="receive_date")
    private String receiveDate;

    @Column(name="complete_date")
    private String completeDate;

    @Column(name="maintain_card_no")
    private String maintainCardNo;

    @Column(name="maintain_card_model")
    private String maintainCardModel;

    @Column(name="maintain_card_category")
    private String maintainCardCategory;

    @Column(name="maintain_card_brand")
    private String maintainCardBrand;

    @Column(name="maintain_user")
    private String maintainUser;

    @Column(name="wx_status")
    private String wxStatus;

    @Column(name="wx_show")
    private String wxShow ;

    @Column(name="wx_method")
    private String wxMethod ;

    @Column(name="wx_result")
    private String wxResult;

    @Column(name="maintain_desc")
    private String maintainDesc;

    @Column(name="maintain_fitting")
    private String maintainFitting;

    @Column(name="create_time")
    @DateFormat
    private Date createTime;

    @Column(name="update_time")
    @DateFormat
    private Date updateTime;

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

    public static LinkedHashMap<String, String> getHeadMap() {

        LinkedHashMap headMap = new LinkedHashMap();

        headMap.put("maintainId", "????????????");
        headMap.put("receiveDate", "????????????");
        headMap.put("completeDate", "????????????");
        headMap.put("maintainCardNo", "???????????????");
        headMap.put("maintainCardModel", "????????????");
        headMap.put("maintainCardCategory", "????????????");
        headMap.put("maintainCardBrand", "????????????");
        headMap.put("maintainUser", "????????????");
        headMap.put("wxStatus", "????????????");
        headMap.put("wxShow", "????????????");
        headMap.put("wxMethod", "????????????");
        headMap.put("wxResult", "????????????");
        headMap.put("maintainDesc", "????????????");
        headMap.put("maintainFitting", "????????????");
        headMap.put("createTime", "????????????");
        headMap.put("updateTime", "????????????");

        return headMap;

    }
}
