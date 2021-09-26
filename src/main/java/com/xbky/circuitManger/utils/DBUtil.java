package com.xbky.circuitManger.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {
    private static Logger log = LoggerFactory.getLogger(DBUtil.class);
    private static Connection connection;
    public synchronized static Connection getConnection() {
        String url = "jdbc:h2:./baseDb/database";
        String driverClass = "org.h2.Driver";
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            log.error("", e);
        } catch (SQLException e) {
            log.error("", e);
        }
        return connection;
    }

    public synchronized static void closeConnection(){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("", e);
            }
        }
    }

    public static Map<String, Object> getBaseInfo() {
        Map<String, Object> result = null;
        try {
            String sql = "select * from soft_info where id = 1";
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
            log.error("", e);
        }
        return result;
    }

    public static int updatePath(String dbPath){
        int result=0;
        try {
            String sql = String.format("update SOFT_INFO set db_path = '%s' where id = 1",dbPath);
            Statement statement = DBUtil.getConnection().createStatement();
            result = statement.executeUpdate(sql);
        } catch (Exception e) {
            log.error("", e);
        }
        return result;
    }

    public static  String  getVersion(){
        Map<String,Object> map = getBaseInfo();
        return ObjectUtil.getString(map.get("version"));
    }

    public static String getDbPath(){
        Map<String,Object> map = getBaseInfo();
        return ObjectUtil.getString(map.get("db_path"));
    }
}

