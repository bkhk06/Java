package cn.adcc.mongorepository;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import java.util.logging.Logger;

@SpringBootApplication
@EnableMongoAuditing

//@EnableMongoRepositories(basePackages="cn.lqdev")//当有些dao不在default page下时 可通过此方法进行注册扫描包
@Slf4j
public class MongorepositoryApplication {

    public static void main(String[] args)  throws Exception{
        SpringApplication.run(MongorepositoryApplication.class, args);
        Logger logger = Logger.getLogger("LoggingDemo");
        logger.info("This is test for Mongo!");
    }

}
