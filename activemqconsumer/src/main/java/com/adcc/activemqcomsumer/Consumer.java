package com.adcc.activemqcomsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Component
public class Consumer {
    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "${queueName}")
    public void receiveQueue(String msg){
        //System.err.println("Queue Consumer received messages: "+msg);
        System.out.println("Consumer收到的报文为:"+msg);
        System.out.println("=================");
    }

    @JmsListener(destination = )
    public void
}
