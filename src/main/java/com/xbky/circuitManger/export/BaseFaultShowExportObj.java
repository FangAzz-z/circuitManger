package com.xbky.circuitManger.export;

import com.xbky.circuitManger.annotation.Column;
import com.xbky.circuitManger.annotation.DateFormat;
import com.xbky.circuitManger.importexcel.BaseImportObj;
import com.xbky.circuitManger.utils.ObjectUtil;

import java.util.Date;
import java.util.LinkedHashMap;

public class BaseFaultShowExportObj {

    @Column(name = "code")
    private String code;

    @Column(name = "content")
    private String content;

    @Column(name = "create_time")
    @DateFormat
    private Date createTime;

    @Column(name = "update_time")
    @DateFormat
    private Date updateTime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static LinkedHashMap<String, String> getHeadMap() {

        LinkedHashMap<String, String> headMap = new LinkedHashMap<>();
        headMap.put("code", "故障代码");
        headMap.put("content", "中文释义");
        headMap.put("createTime", "创建时间");
        headMap.put("updateTime", "修改时间");

        return headMap;
    }

    public static LinkedHashMap<String, String> getHeadMapModel() {

        LinkedHashMap<String, String> headMap = new LinkedHashMap<>();
        headMap.put("code", "故障代码");
        headMap.put("content", "中文释义");

        return headMap;
    }

}
