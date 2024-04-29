package com.adcc.restresponse;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configuration // 1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling // 2.开启定时任务
public class ScheduleService {
	// 3.添加定时任务
	@Scheduled(cron = "0/5 * * * * ?")
	// 或直接指定时间间隔，例如：5秒
	// @Scheduled(fixedRate=5000)
	private void configureTasks() {
		System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
		// 获取当前时间
		// SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss
		// z");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		System.out.println("CurrentTime: " + formatter.format(date));
	}
}
