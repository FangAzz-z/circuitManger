package com.xbky.circuitManger.importexcel;

import com.xbky.circuitManger.utils.ObjectUtil;

import java.util.LinkedHashMap;

public class BaseFaultShowImportObj implements BaseImportObj {

    private String content;
    private String code;

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

        LinkedHashMap<String, String> headMap = new LinkedHashMap<String, String>();
        headMap.put("故障代码", "code");
        headMap.put("中文释义", "content");

        return headMap;
    }

    @Override
    public String valid() {
        if (ObjectUtil.isBlank(this.code)) {
            return "故障代码不能为空";
        }

        if (ObjectUtil.isBlank(this.content)) {
            return "故障现象不能为空";
        }

        return ObjectUtil.EMPTY_STRING;
    }
}
