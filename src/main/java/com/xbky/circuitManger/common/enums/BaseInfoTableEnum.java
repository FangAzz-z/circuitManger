package com.xbky.circuitManger.common.enums;


public enum BaseInfoTableEnum {
    CM_BASE_PT_STAUS("CM_BASE_PT_STAUS","产品状态"),
    CM_BASE_FAULT_SHOW("CM_BASE_FAULT_SHOW","故障现象"),
    CM_BASE_MOUNTINGS_CATEGORY("CM_BASE_MOUNTINGS_CATEGORY","配件种类"),
    CM_BASE_MAINTAIN_ITEM("CM_BASE_MAINTAIN_ITEM","维修措施"),
    CM_BASE_HANDLE_RESULT("CM_BASE_HANDLE_RESULT","处理结果");

    private String tableName;
    private String tableDesc;

    BaseInfoTableEnum(String tableName, String tableDesc) {
        this.tableName = tableName;
        this.tableDesc = tableDesc;
    }

    public String getTableName() {
        return tableName;
    }

    public String getTableDesc() {
        return tableDesc;
    }

}
