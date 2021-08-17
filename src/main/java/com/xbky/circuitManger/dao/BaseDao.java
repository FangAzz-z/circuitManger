package com.xbky.circuitManger.dao;

import com.xbky.circuitManger.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzl
 */
public class BaseDao {
    protected List<Map<String, Object>> queryForList(String sql, Object[] obj) {
        List<Map<String, Object>> returnList = new ArrayList<>();
        try {
            Statement statement;
            ResultSet resultSet;
            if(obj == null) {
                statement = DBUtil.getConnection().createStatement();
            }else{
                statement = DBUtil.getConnection().prepareStatement(sql);
                for (int i = 0; i < obj.length; i++) {
                    ((PreparedStatement) statement).setObject(i,obj[i]);
                }
            }
            resultSet = statement.executeQuery(sql);
            ResultSetMetaData md = resultSet.getMetaData();
            int columnCount = md.getColumnCount();
            while (resultSet.next()) {
                Map rowData = new HashMap<>(columnCount);
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i).toLowerCase(), resultSet.getObject(i));
                }
                returnList.add(rowData);
            }
            // 关闭链接
            resultSet.close();
            statement.close();
            DBUtil.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnList;
    }

    protected Map<String, Object> queryForMap(String sql, Object[] obj) {
        Map<String, Object> result = null;
        try {
            Statement statement = DBUtil.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Map<String, Object>> returnList = new ArrayList<>();
            ResultSetMetaData md = resultSet.getMetaData();
            int columnCount = md.getColumnCount();
            while (resultSet.next()) {
                Map rowData = new HashMap<>(columnCount);
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
            DBUtil.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    protected int insert(String sql, Object[] obj) {
        int result=0;
        try {
            Statement statement;
            ResultSet resultSet;
            if(obj == null) {
                statement = DBUtil.getConnection().createStatement();
            }else{
                statement = DBUtil.getConnection().prepareStatement(sql);
                for (int i = 0; i < obj.length; i++) {
                    ((PreparedStatement) statement).setObject(i,obj[i]);
                }
            }
            boolean bool = statement.execute(sql);
            result = bool?1:0;
        } catch (Exception e) {
            e.printStackTrace();
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
            ResultSet resultSet;
            if(obj == null) {
                statement = DBUtil.getConnection().createStatement();
            }else{
                statement = DBUtil.getConnection().prepareStatement(sql);
                for (int i = 0; i < obj.length; i++) {
                    ((PreparedStatement) statement).setObject(i,obj[i]);
                }
            }
            result = statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
