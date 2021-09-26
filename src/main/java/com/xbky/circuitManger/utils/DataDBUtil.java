package com.xbky.circuitManger.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author:004485
 * @date:2021/9/26 11:14
 */
public class DataDBUtil {

    private static Logger log = LoggerFactory.getLogger(DBUtil.class);
    private static Connection connection;
    private static String db_path = "";
    public synchronized static Connection getConnection() {
        String url = "jdbc:h2:./db/database";
        initDbPath();
        if(ObjectUtil.isNotNull(db_path)){
            url = String.format("jdbc:h2:%s",db_path);
        }
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
    private static void initDbPath(){
        if (ObjectUtil.isNull(db_path)) {
            db_path = DBUtil.getDbPath();
        }
    }

    public static void  resetPath(){
        db_path = DBUtil.getDbPath();
    }


}
