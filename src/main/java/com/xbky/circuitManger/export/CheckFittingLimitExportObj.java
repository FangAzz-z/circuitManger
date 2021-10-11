package com.xbky.circuitManger.export;

import com.xbky.circuitManger.annotation.Column;

import java.util.LinkedHashMap;

public class CheckFittingLimitExportObj {
    @Column(name="fitting_model")
    private String fittingModel;

    @Column(name="fitting_num")
    private String fittingNum;

    @Column(name="fitting_no")
    private String fittingNo;

    @Column(name="fitting_name")
    private String fittingName;

    @Column(name="low_limit")
    private String lowLimit;

    public String getFittingModel() {
        return fittingModel;
    }

    public void setFittingModel(String fittingModel) {
        this.fittingModel = fittingModel;
    }

    public String getFittingNum() {
        return fittingNum;
    }

    public void setFittingNum(String fittingNum) {
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

    public String getLowLimit() {
        return lowLimit;
    }

    public void setLowLimit(String lowLimit) {
        this.lowLimit = lowLimit;
    }

    public static LinkedHashMap<String, String> getHeadMap() {

        LinkedHashMap headMap = new LinkedHashMap();

        headMap.put("fittingNo", "配件编号");
        headMap.put("fittingName", "配件名称");
        headMap.put("fittingModel", "配件型号");
        headMap.put("fittingNum", "配件数量");
        headMap.put("lowLimit", "低限预警");
        return headMap;

    }
}
