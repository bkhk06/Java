package com.adcc.rmqdemo1;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutMessageReceiver {

    @RabbitListener(queues = RabbitConfig.QUEUE1)
    public void receiveMessageFromQueue1(String message) {
        System.out.println("Received from Queue1: " + message);
    }

    @RabbitListener(queues = RabbitConfig.QUEUE2)
    public void receiveMessageFromQueue2(String message) {
        System.out.println("Received from Queue2: " + message);
    }
}
