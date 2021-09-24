package com.xbky.circuitManger.export;

import com.xbky.circuitManger.annotation.Column;
import com.xbky.circuitManger.annotation.DateFormat;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProductTypeExportObj {

    @Column(name="category")
    private String category;


    @Column(name="brand")
    private String brand;

    /**
     *  型号
     */
    @Column(name="model")
    private String model;


    @Column(name="create_time")
    @DateFormat
    private Date createTime;

    @Column(name="update_time")
    @DateFormat
    private Date updateTime;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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
        LinkedHashMap<String, String> headMap = new LinkedHashMap<>();
        headMap.put("category", "类别");
        headMap.put("brand", "品牌");
        headMap.put("model", "型号");
        headMap.put("createTime", "创建时间");
        headMap.put("updateTime", "更新时间");

        return headMap;
    }

    public static LinkedHashMap<String, String> getHeadMapModel() {
        LinkedHashMap<String, String> headMap = new LinkedHashMap<>();
        headMap.put("category", "类别");
        headMap.put("brand", "品牌");
        headMap.put("model", "型号");
        return headMap;
    }
}
