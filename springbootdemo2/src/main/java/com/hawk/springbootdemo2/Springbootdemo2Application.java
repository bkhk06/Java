package com.hawk.springbootdemo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Springbootdemo2Application {
    @RestController
    @EnableAutoConfiguration
    public class demo {
        @RequestMapping("/")
        String home(){
            return "hello demo of Spring Boot!!";
        }
    }
    public static void main(String[] args) {
                SpringApplication.run(Springbootdemo2Application.class, args);
    }

}
