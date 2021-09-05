package com.xbky.circuitManger.dao;

import com.xbky.circuitManger.utils.ObjectUtil;
import org.apache.commons.collections4.CollectionUtils;

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

    public List<Map<String,Object>> queryByExample(String baseTable, String content) {

        StringBuilder sql = new StringBuilder();
        sql.append("select * from ").append(baseTable).append(" where 1 = 1 ");

        if (!ObjectUtil.isBlank(content)) {
            sql.append(" and content like '%").append(content).append("%' ");
        }
        sql.append(" order by update_time desc");

        return super.queryForList(sql.toString(), null);
    }

    public int batchInert(String tableName, List<String> contents) {

        if (CollectionUtils.isEmpty(contents)) {
            return 0;
        }

        StringBuilder sb = new StringBuilder();

        sb.append("insert into ").append(tableName).append(" (content, create_time, update_time) values ");

        for (String content : contents) {
            sb.append("(").append("'").append(content).append("', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),");
        }

        return super.update(sb.substring(0, sb.length() - 1).toString(), null);
    }
}
