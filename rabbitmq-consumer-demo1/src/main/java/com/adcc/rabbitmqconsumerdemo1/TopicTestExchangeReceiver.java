package com.adcc.rabbitmqconsumerdemo1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author LD
 * @version 1.0
 * @date 2021/8/10 10:14
 */
@Component
@RabbitListener(queues = "TestDirectQueue")
public class TopicTestExchangeReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("TestDirectExchange消费者收到消息  : " + testMessage.toString());
    }
}

