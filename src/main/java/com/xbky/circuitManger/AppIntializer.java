package com.xbky.circuitManger;

import com.xbky.circuitManger.utils.DBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppIntializer {
    private static Logger log = LoggerFactory.getLogger(AppIntializer.class);
    public static void init(){
        try {
            Statement statement = DBUtil.getConnection().createStatement();
            // 创建数据表
            // 产品类别型号
            if (!isExist(statement,"CM_PRODECT_TYPE")){
                log.info("数据表->CM_PRODECT_TYPE 初始化");
                String sql = "create table CM_PRODECT_TYPE(" +
                        "id bigint(11) primary key auto_increment, " +
                        "category varchar(20)," +
                        "model varchar(20)," +
                        "brand varchar(20))";
                statement.execute(sql);
                // 添加数据
                statement.executeUpdate("insert into CM_PRODECT_TYPE(category,model,brand) values('冰箱','002','海尔')");
                statement.executeUpdate("insert into CM_PRODECT_TYPE(category,model,brand) values('空调','003','格力')");
                statement.executeUpdate("insert into CM_PRODECT_TYPE(category,model,brand) values('洗衣机','007','美的')");
            }

            //基础设置->产品状态
            if(!isExist(statement,"CM_BASE_PT_STAUS")){
                log.info("数据表->CM_BASE_PT_STAUS 初始化");
                String sql = "create table CM_BASE_PT_STAUS(id bigint(11) primary key auto_increment, content varchar(255),create_time datetime,update_time datetime)";
                statement.execute(sql);
            }
            //基础设置->故障现象
            if(!isExist(statement,"CM_BASE_FAULT_SHOW")){
                log.info("数据表->CM_BASE_FAULT_SHOW 初始化");
                String sql = "create table CM_BASE_FAULT_SHOW(id bigint(11) primary key auto_increment, content varchar(255),create_time datetime,update_time datetime)";
                statement.execute(sql);
            }
            //基础设置->配件种类
            if(!isExist(statement,"CM_BASE_MOUNTINGS_CATEGORY")){
                log.info("数据表->CM_BASE_MOUNTINGS_CATEGORY 初始化");
                String sql = "create table CM_BASE_MOUNTINGS_CATEGORY(id bigint(11) primary key auto_increment, content varchar(255),create_time datetime,update_time datetime)";
                statement.execute(sql);
            }
            //基础设置->维修措施
            if(!isExist(statement,"CM_BASE_MAINTAIN_ITEM")){
                log.info("数据表->CM_BASE_MAINTAIN_ITEM 初始化");
                String sql = "create table CM_BASE_MAINTAIN_ITEM(id bigint(11) primary key auto_increment, content varchar(255),create_time datetime,update_time datetime)";
                statement.execute(sql);
            }
            //基础设置->处理结果
            if(!isExist(statement,"CM_BASE_HANDLE_RESULT")){
                log.info("数据表->CM_BASE_HANDLE_RESULT 初始化");
                String sql = "create table CM_BASE_HANDLE_RESULT(id bigint(11) primary key auto_increment, content varchar(255),create_time datetime,update_time datetime)";
                statement.execute(sql);
            }
            //维修人员
            if(!isExist(statement,"CM_MAINTAIN_USER")){
                log.info("数据表->CM_MAINTAIN_USER 初始化");
                String sql = "create table CM_MAINTAIN_USER(id bigint(11) primary key auto_increment, name varchar(50),department varchar(50),job varchar(50),phone varchar(50),create_time datetime,update_time datetime)";
                statement.execute(sql);
            }
            //配件入库信息
            if(!isExist(statement,"CM_FITTING_IN_INFO")){
                log.info("数据表->CM_FITTING_IN_INFO 初始化");
                String sql = "create table CM_FITTING_IN_INFO(id bigint(11) primary key auto_increment, fitting_no varchar(50),fitting_name varchar(50),job varchar(50),fitting_model varchar(50),unit varchar(20),create_time datetime,update_time datetime)";
                statement.execute(sql);
            }
            //维修登记单
            if(!isExist(statement,"CM_CHECK_MAINTAIN_RECORD")){
                log.info("数据表->CM_CHECK_MAINTAIN_RECORD 初始化");
                String sql = "create table CM_CHECK_MAINTAIN_RECORD(id bigint(11) primary key auto_increment," +
                        " maintain_id varchar(20),receive_date datetime, complete_date datetime," +
                        "maintain_card_no varchar(50),maintain_card_model varchar(50),maintain_card_category varchar(50)," +
                        "maintain_card_brand varchar(50),maintain_user varchar(50),maintain_desc varchar(255)," +
                        "maintain_fitting varchar(50),create_time datetime,update_time datetime)";
                statement.execute(sql);
            }
            //配件登记单
            if(!isExist(statement,"CM_CHECK_FITTING_RECORD")){
                log.info("数据表->CM_CHECK_FITTING_RECORD 初始化");
                String sql = "create table CM_CHECK_FITTING_RECORD(id bigint(11) primary key auto_increment, fitting_model varchar(50),fitting_num varchar(50),fitting_no varchar(50),fitting_name varchar(50),create_time datetime,update_time datetime)";
                statement.execute(sql);
            }
            statement.close();
            DBUtil.closeConnection();
        }catch (SQLException e) {
            log.error("", e);
        }
    }

    private static boolean isExist(Statement statement,String tableName) throws SQLException {
        ResultSet resultSet = statement.executeQuery(String.format("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES  WHERE TABLE_NAME = '%s'",tableName));
        return resultSet.next();
    }
}
