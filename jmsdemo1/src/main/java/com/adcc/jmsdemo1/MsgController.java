package com.adcc.jmsdemo1;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Liu.DA on 2019/9/19
 */
@RestController
public class MsgController {

    private final JmsTemplate jmsTemplate;

    public MsgController(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
    }

    @GetMapping("/sendQueue")
    public String sendMsg(String msg){
        jmsTemplate.convertAndSend("queue.test",msg);
        //jmsTemplate.convertAndSend(new ActiveMQQueue("queue.test"),msg);
        return "SUCCESS";
    }

    @GetMapping("/sendTopic")
    public String sendTopic(String msg) {
        jmsTemplate.convertAndSend(new ActiveMQTopic("topic.test"), msg);
        return "SUCCESS";
    }
}
