package com.adcc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.logging.Logger;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;



public class checkTask implements Runnable {
    private static final Logger logger = Logger.getLogger(checkTask.class.getName());

    @Override
    public void run() {
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间： " + currentTime);
        

        // 配置Logger输出到指定文件
        FileHandler fileHandler;
        try {
            fileHandler = new FileHandler("/home/AppServerLog/check-flight-update-SOBT.log", true);
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            logger.severe("Error occurred while setting up logger: " + e.getMessage());
        }

        
        //执行SQL，更新SOBT为空的航班，使之SOBT=EOBT
        System.out.println("Executing Oracle SQL...");
        try {
            // 加载Oracle JDBC驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");
 
            // 设置数据库连接字符串、用户名和密码
            String url = "jdbc:oracle:thin:@172.22.252.5:15021/atfmsw";
            String username = "fme";
            String password = "fme";
 
            // 建立连接
            Connection conn = DriverManager.getConnection(url, username, password);
 
            // 创建Statement对象执行SQL
            //Statement statement = conn.createStatement();
            //String sql = "select ft.id,ft.flightid,ft.s_deptime,ft.p_deptime,ft.p_depap,ft.p_arrap,ft.r_deptime from fme.fme_today ft where  ft.p_deptime is not null and ft.s_deptime is null and ft.r_deptime is null and ft.flightid='SAG70QP' "; // 替换为你的SQL语句
            String updateSql = "update fme.fme_today ft set ft.s_deptime=ft.p_Deptime where ft.p_deptime is not null and ft.s_deptime is null and ft.mission_date=to_char(sysdate,'YYYYMMDD') and ft.r_deptime is null";
            //statement.executeQuery(sql);

             // 创建PreparedStatement对象
            PreparedStatement preparedStatement = conn.prepareStatement(updateSql);

            // 执行update语句
            int rowsAffected = preparedStatement.executeUpdate();

            // 输出执行情况
            System.out.println(currentTime+"更新成功，影响了 " + rowsAffected + " 行。");
            logger.info(currentTime+"更新成功，影响了 " + rowsAffected + " 行。");
            
 
            // 关闭连接和Statement
            preparedStatement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

