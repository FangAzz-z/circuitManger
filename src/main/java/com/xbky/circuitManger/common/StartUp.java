package com.xbky.circuitManger.common;

import com.xbky.circuitManger.utils.DBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StartUp {
    Logger log = LoggerFactory.getLogger(StartUp.class);
    public void init(){
        try {
            Statement statement = DBUtil.getConnection().createStatement();
            // 创建数据表
            if (!isExist(statement,"CM_PRODECT_TYPE")){
                log.info("数据表->CM_PRODECT_TYPE 初始化");
                String sql = "create table CM_PRODECT_TYPE(" +
                        "id bigint(11) primary key auto_increment, " +
                        "category varchar(20)," +
                        "model varchar(20)," +
                        "brand varchar(20))";
                statement.execute(sql);
                statement.executeUpdate("insert into CM_PRODECT_TYPE(category,model,brand) values('冰箱','002','海尔')");
                statement.executeUpdate("insert into CM_PRODECT_TYPE(category,model,brand) values('空调','003','格力')");
                statement.executeUpdate("insert into CM_PRODECT_TYPE(category,model,brand) values('洗衣机','007','美的')");
            }
            // 添加数据
            statement.close();
            DBUtil.closeConnection();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isExist(Statement statement,String tableName) throws SQLException {
        ResultSet resultSet = statement.executeQuery(String.format("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES  WHERE TABLE_NAME = '%s'",tableName));
        return resultSet.next();
    }
}
