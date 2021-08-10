package com.adcc.rabbitmqconsumerdemo1;

import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.Map;

/**
 * @author LD
 * @version 1.0
 * @date 2021/8/10 10:23
 */

@Component
@RabbitListener(queues = "topic.woman")
public class TopicTotalReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("TopicTotalReceiver消费者收到消息  : " + testMessage.toString());
    }
}

