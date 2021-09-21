package com.xbky.circuitManger;

import com.xbky.circuitManger.utils.DBUtil;
import org.h2.store.fs.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
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
            if (!isExist(statement,"CM_PRODUCT_TYPE")){
                log.info("数据表->CM_PRODUCT_TYPE 初始化");
                String sql = "create table CM_PRODUCT_TYPE(" +
                        "id bigint(11) primary key auto_increment, " +
                        "category varchar(20)," +
                        "model varchar(20)," +
                        "brand varchar(20)," +
                        "create_time datetime," +
                        "update_time datetime)";
                statement.execute(sql);
                // 添加数据
                statement.executeUpdate("insert into CM_PRODUCT_TYPE(category,model,brand) values('冰箱','002','海尔')");
                statement.executeUpdate("insert into CM_PRODUCT_TYPE(category,model,brand) values('空调','003','格力')");
                statement.executeUpdate("insert into CM_PRODUCT_TYPE(category,model,brand) values('洗衣机','007','美的')");
            }

            //基础设置->产品状态
            if(!isExist(statement,"CM_BASE_PT_STATUS")){
                log.info("数据表->CM_BASE_PT_STATUS 初始化");
                String sql = "create table CM_BASE_PT_STATUS(id bigint(11) primary key auto_increment, content varchar(255),create_time datetime,update_time datetime)";
                statement.execute(sql);
            }
            //基础设置->故障现象
            if(!isExist(statement,"CM_BASE_FAULT_SHOW")){
                log.info("数据表->CM_BASE_FAULT_SHOW 初始化");
                String sql = "create table CM_BASE_FAULT_SHOW(id bigint(11) primary key auto_increment, code varchar(100),content varchar(255),create_time datetime,update_time datetime)";
                statement.execute(sql);
            }
            //基础设置->维修措施
            if(!isExist(statement,"CM_BASE_MAINTAIN_METHOD")){
                log.info("数据表->CM_BASE_MAINTAIN_METHOD 初始化");
                String sql = "create table CM_BASE_MAINTAIN_METHOD(id bigint(11) primary key auto_increment, content varchar(255),create_time datetime,update_time datetime)";
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
                String sql = "create table CM_MAINTAIN_USER(id bigint(11) primary key auto_increment, name varchar(50),sex varchar(5),department varchar(50),job varchar(50),phone varchar(50),create_time datetime,update_time datetime)";
                statement.execute(sql);
            }
            //配件入库信息
            if(!isExist(statement,"CM_FITTING_INTO_INFO")){
                log.info("数据表->CM_FITTING_INTO_INFO 初始化");
                String sql = "create table CM_FITTING_INTO_INFO(id bigint(11) primary key auto_increment, fitting_no varchar(50),fitting_name varchar(50),fitting_model varchar(50),unit varchar(20),factory varchar(20),create_time datetime,update_time datetime)";
                statement.execute(sql);
            }
            //维修登记单
            if(!isExist(statement,"CM_CHECK_MAINTAIN_RECORD")){
                log.info("数据表->CM_CHECK_MAINTAIN_RECORD 初始化");
                String sql = "create table CM_CHECK_MAINTAIN_RECORD(id bigint(11) primary key auto_increment," +
                        " maintain_id varchar(20),receive_date datetime, complete_date datetime," +
                        "maintain_card_no varchar(50),maintain_card_model varchar(50),maintain_card_category varchar(50)," +
                        "maintain_card_brand varchar(50),maintain_user varchar(50),wx_status varchar(50)," +
                        "wx_show varchar(50),wx_method varchar(50),wx_result varchar(50),maintain_desc varchar(255)," +
                        "maintain_fitting varchar(255),create_time datetime,update_time datetime)";
                statement.execute(sql);
            }
            //配件库存单
            if(!isExist(statement,"CM_CHECK_FITTING_RECORD")){
                log.info("数据表->CM_CHECK_FITTING_RECORD 初始化");
                String sql = "create table CM_CHECK_FITTING_RECORD(id bigint(11) primary key auto_increment, fitting_model varchar(50),fitting_num int,fitting_no varchar(50),fitting_name varchar(50),low_limit int,create_time datetime,update_time datetime)";
                statement.execute(sql);
            }
            //程序配置地址
            if(!isExist(statement,"CM_PROGRAM_LOCATION")){
                log.info("数据表->CM_PROGRAM_LOCATION 初始化");
                String sql = "create table CM_PROGRAM_LOCATION(id bigint(11) primary key auto_increment, program_name varchar(50),program_location varchar(255),create_time datetime,update_time datetime)";
                statement.execute(sql);
                // 添加数据
                statement.executeUpdate("insert into CM_PROGRAM_LOCATION(program_name,program_location,create_time,update_time) values('程序下载','',now(),now())");
                statement.executeUpdate("insert into CM_PROGRAM_LOCATION(program_name,program_location,create_time,update_time) values('通讯连接','',now(),now())");
                statement.executeUpdate("insert into CM_PROGRAM_LOCATION(program_name,program_location,create_time,update_time) values('打印终端连接','C:\\Program Files (x86)\\Brother\\Ptedit52\\ptedit52.exe',now(),now())");
                statement.executeUpdate("insert into CM_PROGRAM_LOCATION(program_name,program_location,create_time,update_time) values('维修板卡测试','C:/GETrans/wPTU_801/bin/AC/ptuac.bat',now(),now())");
            }

            //程序配置地址
            if(!isExist(statement,"CM_SYSTEM_USER")){
                log.info("数据表->CM_SYSTEM_USER 初始化");
                String sql = "create table CM_SYSTEM_USER(id bigint(11) primary key auto_increment, user_name varchar(50),user_password varchar(50),create_time datetime,update_time datetime)";
                statement.execute(sql);
                // 添加数据
                statement.executeUpdate("insert into CM_SYSTEM_USER(user_name,user_password,create_time,update_time) values('admin','admin',now(),now())");
            }
            if(!isExist(statement,"CM_SYSTEM_USER_MODULE")){
                log.info("数据表->CM_SYSTEM_USER_MODULE 初始化");
                String sql = "create table CM_SYSTEM_USER_MODULE(id bigint(11) primary key auto_increment, user_name varchar(50),module_name varchar(50),create_time datetime,update_time datetime)";
                statement.execute(sql);
            }
            if(!isExist(statement,"CM_SYSTEM_SET_SCREEN")){
                log.info("数据表->CM_SYSTEM_SET_SCREEN 初始化");
                String sql = "create table CM_SYSTEM_SET_SCREEN(id bigint(11) primary key , is_set int,image_url varchar(255),font_color varchar(50),create_time datetime,update_time datetime)";
                statement.execute(sql);
                statement.executeUpdate("insert into CM_SYSTEM_SET_SCREEN(id,is_set,image_url,font_color,create_time,update_time) values(1,0,'','',now(),now())");
            }
            statement.close();
            DBUtil.closeConnection();
            buildHtml();
            buildLbx();
        }catch (SQLException e) {
            log.error("", e);
        }
    }

    private static boolean isExist(Statement statement,String tableName) throws SQLException {
        ResultSet resultSet = statement.executeQuery(String.format("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES  WHERE TABLE_NAME = '%s'",tableName));
        return resultSet.next();
    }

    // /image/image
    private static void buildHtml() {
        try {
            log.info("html模板生产->start");
            InputStream input = AppIntializer.class.getClassLoader().getResourceAsStream("html/test0.html");
            File rootFile = new File("html/");
            if(!rootFile.exists()){
                rootFile.mkdir();
            }
            File test = new File("html/test.html");
            if(!test.exists()){
                test.createNewFile();
            }
            File test0 = new File("html/test0.html");
            if(!test.exists()){
                test0.createNewFile();
            }

            BufferedReader br =  new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
            String b="";
            StringBuffer sb = new StringBuffer();
            while((b = br.readLine())!=null){
                    //得到文件内容放到sb中
                    sb.append(b);
                    //这里可以写自己想对每一行的处理代码
            }
            String s = sb.toString();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(test),StandardCharsets.UTF_8));
            bw.write(s);
            bw.flush();
            BufferedWriter bw0 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(test0),StandardCharsets.UTF_8));
            bw0.write(s);
            bw0.flush();
            log.info("html模板生产->end");
        } catch (IOException e) {
            log.error("",e);
        }
    }
    private static void buildLbx() {
        try{
            log.info("lbx模板生产->start");
            InputStream input = AppIntializer.class.getClassLoader().getResourceAsStream("html/wx2.lbx");
            File rootFile = new File("html/");
            if(!rootFile.exists()){
                rootFile.mkdir();
            }
            File test = new File("html/wx2.lbx");
            if(!test.exists()){
                test.createNewFile();
            }
            byte[] buffer = new byte[4048];
            input.read(buffer);
            FileOutputStream bw = new FileOutputStream(test);
            bw.write(buffer);
            bw.flush();
            log.info("lbx模板生产->end");
        } catch (IOException e) {
            log.error("",e);
        }
    }
}
