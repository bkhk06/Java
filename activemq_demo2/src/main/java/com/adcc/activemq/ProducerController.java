package com.adcc.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jms.Destination;
import javax.jms.Topic;
import java.util.Queue;

/**
 * Created by Liu.DA on 2019/9/3
 */
public class ProducerController {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    /*
     * 消息生产者
     */
    @RequestMapping("/sendmsg")
    public void sendmsg(String msg) {
        // 指定消息发送的目的地及内容
        this.jmsMessagingTemplate.convertAndSend((Destination) this.queue, msg);
    }

    @RequestMapping("/send")
    public void send(String msg) {
        // 指定消息发送的目的地及内容
        System.out.println("@@@@@@@@@@@@@@" + msg);
        this.jmsMessagingTemplate.convertAndSend(this.topic, msg);
    }
}
