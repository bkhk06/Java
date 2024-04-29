package com.adcc.rabbitmqconsumerdemo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.*;

@SpringBootApplication
public class RabbitmqConsumerDemo2Application {

    public static void main(String[] args) {
        run(RabbitmqConsumerDemo1Application.class, args);
    }

}
