package com.adcc.activemqproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class ActivemqproducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivemqproducerApplication.class, args);
    }

}
