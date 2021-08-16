package com.xbky.circuitManger.common;

import com.xbky.circuitManger.utils.DBUtil;

import java.sql.SQLException;
import java.sql.Statement;

public class StartUp {
    public void init(){
        try {
            Statement statement = DBUtil.getConnection().createStatement();
            // 创建数据表
            statement.execute("DROP TABLE IF EXISTS cm_prodect_type");
            statement.execute("create table cm_prodect_type(id int(11) primary key auto_increment, category varchar(20),model varchar(20),brand varchar(20))");
            // 添加数据
            statement.executeUpdate("insert into cm_prodect_type(category,model,brand) values('冰箱','002','海尔')");
            statement.executeUpdate("insert into cm_prodect_type(category,model,brand) values('空调','003','格力')");
            statement.executeUpdate("insert into cm_prodect_type(category,model,brand) values('洗衣机','007','美的')");
            statement.close();
            DBUtil.closeConnection();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
