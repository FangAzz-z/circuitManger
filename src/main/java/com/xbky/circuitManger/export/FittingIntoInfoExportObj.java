package com.xbky.circuitManger.export;

import com.xbky.circuitManger.annotation.Column;
import com.xbky.circuitManger.annotation.DateFormat;

import java.util.Date;
import java.util.LinkedHashMap;

public class FittingIntoInfoExportObj {


    @Column(name = "id")
    private Long id;

    @Column(name = "fitting_no")
    private String fittingNo;

    @Column(name = "fitting_name")
    private String fittingName;

    @Column(name = "fitting_model")
    private String fittingModel;

    @Column(name = "packaging")
    private String packaging;

    @Column(name = "factory")
    private String factory;

    @Column(name = "unit")
    private String unit;

    @Column(name = "create_time")
    @DateFormat
    private Date createTime;
    @Column(name = "update_time")
    @DateFormat
    private Date updateTime;


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

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
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

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public static LinkedHashMap<String, String> getHeadMap() {
        LinkedHashMap<String, String> headMap = new LinkedHashMap<String, String>();
        headMap.put("id", "ID");
        headMap.put("fittingNo", "????????????");
        headMap.put("fittingName", "????????????");
        headMap.put("fittingModel", "????????????");
        headMap.put("packaging", "??????");
        headMap.put("factory", "??????");
        headMap.put("unit", "??????");
        headMap.put("createTime", "????????????");
        headMap.put("updateTime", "????????????");

        return headMap;
    }

    public static LinkedHashMap<String, String> getHeadMapModel() {
        LinkedHashMap<String, String> headMap = new LinkedHashMap<String, String>();
        headMap.put("fittingNo", "????????????");
        headMap.put("fittingName", "????????????");
        headMap.put("fittingModel", "????????????");
        headMap.put("packaging", "??????");
        headMap.put("factory", "??????");
        headMap.put("unit", "??????");

        return headMap;
    }
}
