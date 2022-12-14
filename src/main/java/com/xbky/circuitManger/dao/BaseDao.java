package com.xbky.circuitManger.dao;

import com.xbky.circuitManger.utils.DataDBUtil;
import com.xbky.circuitManger.utils.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzl
 */
public class BaseDao {
    private static Logger log = LoggerFactory.getLogger(BaseDao.class);
    protected List<Map<String, Object>> queryForList(String sql, Object[] obj) {
        List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
        try {
            Statement statement;
            ResultSet resultSet;
            if(obj == null) {
                statement = DataDBUtil.getConnection().createStatement();
            }else{
                statement = DataDBUtil.getConnection().prepareStatement(sql);
                for (int i = 0; i < obj.length; i++) {
                    ((PreparedStatement) statement).setObject(i,obj[i-1]);
                }
            }
            resultSet = statement.executeQuery(sql);
            ResultSetMetaData md = resultSet.getMetaData();
            int columnCount = md.getColumnCount();
            while (resultSet.next()) {
                Map rowData = new HashMap(columnCount);
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i).toLowerCase(), resultSet.getObject(i));
                }
                returnList.add(rowData);
            }
            // 关闭链接
            resultSet.close();
            statement.close();
            DataDBUtil.closeConnection();
        } catch (Exception e) {
            log.error("", e);
        }
        return returnList;
    }

    protected Map<String, Object> queryForMap(String sql, Object[] obj) {
        Map<String, Object> result = null;
        try {
            Statement statement = DataDBUtil.getConnection().createStatement();
            if(obj == null) {
                statement = DataDBUtil.getConnection().createStatement();
            }else{
                statement = DataDBUtil.getConnection().prepareStatement(sql);
                for (int i = 0; i < obj.length; i++) {
                    ((PreparedStatement) statement).setObject(i,obj[i-1]);
                }
            }
            ResultSet resultSet = statement.executeQuery(sql);

            List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
            ResultSetMetaData md = resultSet.getMetaData();
            int columnCount = md.getColumnCount();
            while (resultSet.next()) {
                Map rowData = new HashMap(columnCount);
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i).toLowerCase(), resultSet.getObject(i));
                }
                returnList.add(rowData);
            }
            if(returnList.size()>0) {
                result = returnList.get(0);
            }
            resultSet.close();
            statement.close();
            DataDBUtil.closeConnection();
        } catch (Exception e) {
            log.error("", e);
        }
        return result;
    }

    protected int insert(String sql, Object[] obj) {
        int result=0;
        try {
            Statement statement;
            if(obj == null) {
                statement = DataDBUtil.getConnection().createStatement();
            }else{
                statement = DataDBUtil.getConnection().prepareStatement(sql);
                for (int i = 1; i <= obj.length; i++) {
                    ((PreparedStatement) statement).setObject(i,obj[i-1]);
                }
            }
            boolean bool = statement.execute(sql);
            result = bool?1:0;
        } catch (Exception e) {
            log.error("", e);
        }
        return result;
    }

    protected int insertBatch(String sql){
        return 0;
    }

    protected int update(String sql,Object[] obj){
        int result=0;
        try {
            Statement statement;
            if(obj == null) {
                statement = DataDBUtil.getConnection().createStatement();
            }else{
                statement = DataDBUtil.getConnection().prepareStatement(sql);
                for (int i = 1; i <= obj.length; i++) {
                    ((PreparedStatement) statement).setObject(i,obj[i-1]);
                }
            }
            result = statement.executeUpdate(sql);
        } catch (Exception e) {
            log.error("", e);
        }
        return result;
    }

    public List<Map<String,Object>> commonQueryAll(String baseTable){
        String sql = String.format("select * from %s order by update_time desc",baseTable);
        List<Map<String,Object>> result = queryForList(sql, null);
        return result;
    }
    public int queryCount(String baseTable){
        String sql = String.format("select count(1) from %s",baseTable);
        Map<String,Object> result = queryForMap(sql, null);
        return ObjectUtil.getInt(result.get("count(1)"));
    }

    public Map<String,Object> commonQueryAll(String baseTable,int pageNo,int pageSize){
        String sql = String.format("select * from %s order by update_time desc limit %s,%s",baseTable,pageNo*10,pageSize);
        int count = queryCount(baseTable);
        int total = (count/(pageSize+1))+1;
        List<Map<String,Object>> result = queryForList(sql, null);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", result);
        map.put("total", total);
        return map;
    }

    public int commonDeleteById(String baseTable,String id){
        String sql = String.format("delete from %s where id = %s",baseTable,id);
        Object[] obj = new Object[]{id};
        return update(sql, null);
    }
}
