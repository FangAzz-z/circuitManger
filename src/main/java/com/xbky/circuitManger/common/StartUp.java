package com.xbky.circuitManger.common;

import com.xbky.circuitManger.utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StartUp {
    public void init(){
        try {
            Statement statement = DBUtil.getConnection().createStatement();
            // 创建数据表
            ResultSet resultSet = statement.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES  WHERE TABLE_NAME = 'CM_PRODECT_TYPE'");
            if (!resultSet.next()){
                System.out.println("--->CM_PRODECT_TYPE init");
                String sql = "create table CM_PRODECT_TYPE(" +
                        "id int(11) primary key auto_increment, " +
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
}
