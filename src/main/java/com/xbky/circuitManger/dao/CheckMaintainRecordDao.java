package com.xbky.circuitManger.dao;

import com.xbky.circuitManger.entity.CheckMaintainRecord;

public class CheckMaintainRecordDao extends BaseDao {
    public int add(CheckMaintainRecord record){
        String sql = "insert into CM_CHECK_MAINTAIN_RECORD()values()";
        Object[] obj = new Object[]{};
        return super.insert(sql,obj);
    }
    public int modify(CheckMaintainRecord record){
        return 0;
    }
}
