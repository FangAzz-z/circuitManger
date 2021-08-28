package com.xbky.circuitManger.dao;

import java.util.List;
import java.util.Map;

public class BaseInfoDao extends BaseDao {

    public int updateById(String baseTable,String content,String id){
        String sql = String.format("update %s set content ='%s',update_time = now() where id = '%s'",baseTable,content,id);
        return super.update(sql, null);
    }

    public int add(String baseTable, String content) {
        String sql = String.format("insert into %s(content,create_time,update_time) values('%s',now(),now())",baseTable,content);
        return super.insert(sql, null);
    }

    public List<Map<String,Object>> queryByExample(String baseTable, String content){
        String sql = String.format("select * from %s where content like '%s'",baseTable,content+"%");
        return super.queryForList(sql, null);
    }

}
