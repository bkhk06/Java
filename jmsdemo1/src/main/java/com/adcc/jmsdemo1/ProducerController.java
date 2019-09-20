package com.adcc.jmsdemo1;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Liu.DA on 2019/9/19
 */
@RestController
public class ProducerController {

    private final JmsTemplate jmsTemplate;

    public ProducerController(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
    }

    @Value("${queueName}")
    private String queueName;

    @GetMapping("/sendQueue")
    public String sendMsg(String msg){
        //jmsTemplate.convertAndSend(queueName,msg);
        // 这么写的话，即便启用pub-sub模式，不需要增加额外配置JmsListenerContainerFactory，也可以实现Topic和Queue共存。
        jmsTemplate.convertAndSend(new ActiveMQQueue(queueName),msg);
        return "SUCCESS";
    }

    @Value("${topicName}")
    private String topicName;

    @GetMapping("/sendTopic")
    public String sendTopic(String msg) {
        jmsTemplate.convertAndSend(new ActiveMQTopic(topicName), msg);
        return "SUCCESS";
    }
}
