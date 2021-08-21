package com.xbky.circuitManger.dao;

import java.util.List;
import java.util.Map;

public class BaseInfoDao extends BaseDao {

    public int updateById(String baseTable,String content,String id){
        String sql = String.format("update %s set content =?,update_time = now() where id = ?",baseTable);
        Object[] obj = new Object[]{content,id};
        return super.update(sql, obj);
    }

    public int add(String baseTable, String content) {
        String sql = String.format("insert into %s(content,create_time,update_time) values(?,now(),now())",baseTable);
        Object[] obj = new Object[]{content};
        return super.insert(sql, obj);
    }

}
