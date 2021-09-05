package com.xbky.circuitManger.export;

import com.xbky.circuitManger.annotation.Column;
import com.xbky.circuitManger.annotation.DateFormat;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProductTypeExportObj {

    @Column(name="category")
    private String category;

    /**
     *  型号
     */
    @Column(name="model")
    private String model;

    @Column(name="brand")
    private String brand;

    @Column(name="create_time")
    @DateFormat
    private Date createTime;

    @Column(name="update_time")
    @DateFormat
    private Date updateTime;

    public static LinkedHashMap<String, String> getExportHeadMap() {
        LinkedHashMap<String, String> headMap = new LinkedHashMap<>();
        headMap.put("category", "类别");
        headMap.put("model", "型号");
        headMap.put("brand", "品牌");
        headMap.put("createTime", "创建时间");
        headMap.put("updateTime", "更新时间");

        return headMap;
    }

}
