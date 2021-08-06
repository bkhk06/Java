package com.adcc.rabbitmqconsumerdemo1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author LD
 * @version 1.0
 * @date 2021/8/6 14:35
 */
@Component
@RabbitListener(queues = "TestDirectQueue")//监听的队列名称
public class DirectReceiver {

    @RabbitHandler
    public void process(Map msg) {
        System.out.println("DirectReceiver消费者收到消息  : " + msg.toString());
    }

}
