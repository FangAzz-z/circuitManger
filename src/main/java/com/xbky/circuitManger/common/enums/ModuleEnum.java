package com.xbky.circuitManger.common.enums;


import java.util.Arrays;
import java.util.List;

public enum ModuleEnum {

    wxManager(1,"维修管理","wxManager"),
    cxDown(2,"程序下载","cxDown"),
    txConnect(3,"通讯连接","txConnect"),
    printer(4,"打印终端连接","printer"),
    bkTest(5,"维修板卡测试","bkTest"),
    baseChange(6,"基础设置","baseChange"),
    wxCheck(7,"维修登记","wxCheck");


    private Integer key;
    private String name;
    private String code;

    ModuleEnum(Integer key,String name,String code){
        this.key  = key;
        this.name = name;
        this.code = code;
    }


    public Integer getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }




}
