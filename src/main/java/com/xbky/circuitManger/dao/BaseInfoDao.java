package com.xbky.circuitManger.dao;

import com.xbky.circuitManger.importexcel.BaseFaultShowImportObj;
import com.xbky.circuitManger.utils.ObjectUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

public class BaseInfoDao extends BaseDao {

    public int updateById(String baseTable,String content,String id){
        String sql = String.format("update %s set content ='%s',update_time = now() where id = '%s'",baseTable,content,id);
        return super.update(sql, null);
    }

    public int add(String baseTable, String content) {
        String sql = String.format("insert into %s(content,create_time,update_time) values('%s',now(),now())",baseTable,content);
        return super.insert(sql, null);
    }

    public int updateByIdV2(String baseTable,String content,String code,String id){
        StringBuffer sql = new StringBuffer("update "+baseTable+" set update_time = now() ");
        if (ObjectUtil.isNotNull(content)) {
            sql.append(String.format(",content = '%s'",content));
        }
        if (ObjectUtil.isNotNull(code)) {
            sql.append(String.format(",code = '%s'",code));
        }
        sql.append(String.format(" where id = '%s'",id));
        sql.append(" order by update_time desc");
        return super.update(sql.toString(),null);
    }

    public int addV2(String baseTable, String content,String code) {
        String sql = String.format("insert into %s(content,code,create_time,update_time) values('%s','%s',now(),now())",baseTable,content,code);
        return super.insert(sql, null);
    }


    public boolean isExistSome(String baseTable, String content,String code){
        StringBuffer sql = new StringBuffer("");
        sql.append("select * from ").append(baseTable).append(" where 1 = 1 ");

        if (!ObjectUtil.isBlank(content)) {
            sql.append(" and content = '%").append(content).append("' ");
        }
        if (!ObjectUtil.isBlank(code)) {
            sql.append(" and code = '%").append(code).append("' ");
        }
        sql.append(" order by update_time desc");
        Boolean result = ObjectUtil.isNotEmpty(super.queryForList(sql.toString(), null))?true:false;
        return result;
    }

    public List<Map<String,Object>> queryByExample(String baseTable, String content) {

        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select * from ").append(baseTable).append(" where 1 = 1 ");

            if (ObjectUtil.isNotNull(content)) {
                sql.append(" and content like '%").append(content).append("%' ");
            }
            sql.append(" order by update_time desc");

            return super.queryForList(sql.toString(), null);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Map<String,Object>> queryByExampleV2(String baseTable, String content,String code) {

        StringBuilder sql = new StringBuilder();
        sql.append("select * from ").append(baseTable).append(" where 1 = 1 ");

        if (!ObjectUtil.isBlank(content)) {
            sql.append(" and content like '%").append(content).append("%' ");
        }
        if (!ObjectUtil.isBlank(code)) {
            sql.append(" and code like '%").append(code).append("%' ");
        }
        sql.append(" order by update_time desc");

        return super.queryForList(sql.toString(), null);
    }

    public Map<String,Object> queryByExample(String baseTable, String content,int pageNo,int pageSize) {

        StringBuilder sql = new StringBuilder();
        sql.append("select * from ").append(baseTable).append(" where 1 = 1 ");

        if (!ObjectUtil.isBlank(content)) {
            sql.append(" and content like '%").append(content).append("%' ");
        }
        int count =  super.queryForList(sql.toString(), null).size();
        int total =  (count  +  pageSize  - 1) / pageSize;
        sql.append(String.format(" order by update_time desc  limit %s,%s ",pageNo*pageSize,pageSize));
        List<Map<String,Object>> list =  super.queryForList(sql.toString(), null);
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        map.put("total", total);
        return map;
    }

    public Map<String,Object> queryByExampleV2(String baseTable, String content, String code,int pageNo,int pageSize) {

        StringBuilder sql = new StringBuilder();
        sql.append("select * from ").append(baseTable).append(" where 1 = 1 ");

        if (!ObjectUtil.isBlank(content)) {
            sql.append(" and LOWER(content) like '%").append(content.toLowerCase()).append("%' ");
        }
        if(!ObjectUtil.isBlank(code)){
            sql.append(" and LOWER(code) like '%").append(code.toLowerCase()).append("%' ");
        }
        int count =  super.queryForList(sql.toString(), null).size();
        int total =  (count  +  pageSize  - 1) / pageSize;
        sql.append(String.format(" order by update_time desc  limit %s,%s ",pageNo*pageSize,pageSize));
        List<Map<String,Object>> list =  super.queryForList(sql.toString(), null);
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        map.put("total", total);
        return map;
    }




    public int batchInert(String tableName, List<BaseFaultShowImportObj> data) {

        if (CollectionUtils.isEmpty(data)) {
            return 0;
        }

        StringBuilder sb = new StringBuilder();

        sb.append("insert into ").append(tableName).append(" (code,content, create_time, update_time) values ");

        for (BaseFaultShowImportObj obj : data) {
            sb.append("(").append("'").append(obj.getCode()+"',").append("'").append(obj.getContent()).append("', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),");
        }

        return super.update(sb.substring(0, sb.length() - 1).toString(), null);
    }
}
