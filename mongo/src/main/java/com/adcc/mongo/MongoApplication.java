package com.adcc.mongo;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@SpringBootApplication
@EnableMongoAuditing
//@EnableMongoRepositories(basePackages="cn.lqdev")//当有些dao不在default page下时 可通过此方法进行注册扫描包
@Slf4j
public class MongoApplication {

    public static void main(String[] args) throws Exception {
       // Logger logger = null;
        ConfigurableApplicationContext run = SpringApplication.run(MongoApplication.class, args);
       // logger.info("spring-boot-mongodb 启动!");
    }

}
