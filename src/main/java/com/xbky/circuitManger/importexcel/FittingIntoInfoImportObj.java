package com.xbky.circuitManger.importexcel;

import com.xbky.circuitManger.utils.ObjectUtil;

import java.util.LinkedHashMap;

public class FittingIntoInfoImportObj implements BaseImportObj{

    private String fittingNo;

    private String fittingName;

    private String fittingModel;

    private String packaging;

    private String factory;

    private String unit;


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

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    @Override
    public String valid() {
        if (ObjectUtil.isBlank(this.fittingNo)) {
            return "配件编号不能为空";
        }

        if (ObjectUtil.isBlank(this.fittingName)) {
            return "配件名称不能为空";
        }

        if (ObjectUtil.isBlank(this.fittingModel)) {
            return "配件型号不能为空";
        }

        if (ObjectUtil.isBlank(this.packaging)) {
            return "封装不能为空";
        }
        if (ObjectUtil.isBlank(this.factory)) {
            return "厂家不能为空";
        }
        if (ObjectUtil.isBlank(this.unit)) {
            return "单位不能为空";
        }

        return ObjectUtil.EMPTY_STRING;
    }

    public static LinkedHashMap<String, String> getHeadMap() {
        LinkedHashMap<String, String> headMap = new LinkedHashMap<>();
        headMap.put( "配件编号","fittingNo");
        headMap.put("配件名称","fittingName");
        headMap.put( "配件型号","fittingModel");
        headMap.put("封装","packaging");
        headMap.put("厂家","factory");
        headMap.put("单位","unit");

        return headMap;
    }
}
