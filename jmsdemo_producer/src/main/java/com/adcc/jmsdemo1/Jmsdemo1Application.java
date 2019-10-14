package com.adcc.jmsdemo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class Jmsdemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Jmsdemo1Application.class, args);
    }

}
