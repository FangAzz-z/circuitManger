package com.xbky.circuitManger.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static Logger log = LoggerFactory.getLogger(DBUtil.class);
    private static Connection connection;
    public synchronized static Connection getConnection() {
        String url = "jdbc:h2:./db/database";
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
}

