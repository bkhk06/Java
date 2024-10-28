package com.adcc.flightsobtcheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
//@ConfigurationProperties(prefix="spring.datasource")
public class UpdateSDeptimeTask {

    private static final Logger logger = LoggerFactory.getLogger(UpdateSDeptimeTask.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    //private final DataSource dataSource;

    @Scheduled(fixedRate = 300000) // 每5分钟执行一次
    public void update() {
        String sql = "update fme.fme_today ft set ft.s_deptime=ft.p_Deptime where ft.p_deptime is not null and ft.s_deptime is null and ft.mission_date=to_char(sysdate,'YYYYMMDD') and ft.r_deptime is null";
        jdbcTemplate.execute(sql);
    }

    /*
    public UpdateSDeptimeTask(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    */

    //@Scheduled(cron = "0 */5 * * * ?")
    /*public void updateSDeptime() {
        String sql = "update fme.fme_today ft set ft.s_deptime=ft.p_Deptime where ft.p_deptime is not null and ft.s_deptime is null and ft.mission_date=to_char(sysdate,'YYYYMMDD') and ft.r_deptime is null";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected+rowsAffected);
            logger.info("更新了{}行数据", rowsAffected);
        } catch (SQLException e) {
            logger.error("更新s_deptime失败", e);
        }
    
    }*/
}

