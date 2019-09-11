package com.adcc.activemqdemo4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfiguration.class)
@EnableAutoConfiguration
@ComponentScan({ "api.web", "api.rest", "api.service" })
@EnableMongoRepositories
@EnableJpaRepositories
public class ActivemqDemo4Application {

    public static void main(String[] args) {
        SpringApplication.run(ActivemqDemo4Application.class, args);
    }

}
