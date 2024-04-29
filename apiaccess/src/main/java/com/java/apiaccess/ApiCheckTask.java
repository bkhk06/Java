package com.java.apiaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ApiCheckTask {

    @Autowired
    private ApiCheckService apiCheckService;

    @Scheduled(fixedRate = 5000) // 每5秒执行一次
    public void checkApi() {
        boolean isApiOk = apiCheckService.checkApi();
        System.out.println("API检测结果：" + (isApiOk ? "正常" : "异常"));
    }
}
