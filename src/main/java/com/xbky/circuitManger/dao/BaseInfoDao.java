package com.xbky.circuitManger.dao;

import java.util.List;
import java.util.Map;

public class BaseInfoDao extends BaseDao {

    public List<Map<String,Object>> queryAll(String baseTable){
        String sql = String.format("select id,content,create_time,update_time from %s",baseTable);
        List<Map<String,Object>> result = super.queryForList(sql, null);
        return result;
    }
    public int deleteById(String baseTable,String id){
        String sql = String.format("delete from %s where id = ?",baseTable);
        Object[] obj = new Object[]{id};
        return super.update(sql, obj);
    }

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
