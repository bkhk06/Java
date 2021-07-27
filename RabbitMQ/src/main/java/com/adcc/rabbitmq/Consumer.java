package com.adcc.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者1
 *
 * @author young
 * @since 2018-03-21 15:15
 */
@Component
@RabbitListener(queues = "helloQueue")
public class Consumer {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver1  : " + hello);
    }
}
