package com.adcc.activemqdemo5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms//start JMS class
public class Activemqdemo5Application {

    public static void main(String[] args) {
        SpringApplication.run(Activemqdemo5Application.class, args);
    }

}
