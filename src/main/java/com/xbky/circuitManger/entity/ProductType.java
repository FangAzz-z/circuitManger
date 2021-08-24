package com.xbky.circuitManger.entity;

import java.util.Date;

public class ProductType {



    /**
     *  唯一主键
     */
    private Long id;
    /**
     *  类别
     */
    private String category;

    /**
     *  型号
     */
    private String model;

    /**
     *  品牌
     */
    private String brand;

    private Date createTime;
    private Date updateTime;

    public ProductType(){

    }
    public ProductType(Long id,String category,String model,String brand){
        this.id = id;
        this.category = category;
        this.model = model;
        this.brand = brand;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}


