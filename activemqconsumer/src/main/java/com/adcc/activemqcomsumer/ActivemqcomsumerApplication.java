package com.adcc.activemqcomsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class ActivemqcomsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivemqcomsumerApplication.class, args);
    }

}
