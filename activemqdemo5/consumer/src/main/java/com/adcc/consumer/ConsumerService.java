package com.adcc.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * Created by Liu.DA on 2019/9/19
 */
@Component
public class ConsumerService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    // 使用JmsListener配置消费者监听的队列，其中msg是接收到的消息
    @JmsListener(destination = "ActiveMQQueueTest")
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    @SendTo("SQueue")
    public String handleMessage(String msg){
        System.out.println("Successfully receieved message: "+msg);
        return "Successfully receieved message: " +msg;
    }
}
