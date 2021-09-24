package com.xbky.circuitManger.importexcel;

import com.xbky.circuitManger.annotation.Column;
import com.xbky.circuitManger.utils.ObjectUtil;

import java.util.LinkedHashMap;

/**
 * @description:
 * @author:004485
 * @date:2021/9/24 14:16
 */
public class ProductTypeImportObj implements BaseImportObj{

    private String category;

    private String brand;

    private String model;

    @Override
    public String valid() {
        if (ObjectUtil.isBlank(this.category)) {
            return "类别不能为空";
        }
        if (ObjectUtil.isBlank(this.brand)) {
            return "品牌不能为空";
        }
        if (ObjectUtil.isBlank(this.model)) {
            return "型号不能为空";
        }
        return ObjectUtil.EMPTY_STRING;
    }

    public static LinkedHashMap<String, String> getHeadMap() {

        LinkedHashMap<String, String> headMap = new LinkedHashMap<>();
        headMap.put("类别", "category");
        headMap.put("品牌", "brand");
        headMap.put("型号", "model");

        return headMap;
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
}
