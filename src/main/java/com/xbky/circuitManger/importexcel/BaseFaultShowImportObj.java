package com.xbky.circuitManger.importexcel;

import com.xbky.circuitManger.utils.ObjectUtil;

import java.util.LinkedHashMap;

public class BaseFaultShowImportObj implements BaseImportObj {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static LinkedHashMap<String, String> getHeadMap() {

        LinkedHashMap<String, String> headMap = new LinkedHashMap<>();
        headMap.put("故障原因", "content");

        return headMap;
    }

    @Override
    public String valid() {
        if (ObjectUtil.isBlank(this.content)) {
            return "故障原因不能为空";
        }

        return ObjectUtil.EMPTY_STRING;
    }
}
