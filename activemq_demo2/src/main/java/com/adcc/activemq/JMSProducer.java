package com.adcc.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by Liu.DA on 2019/9/18
 */
@Component
public class JMSProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(ActiveMQQueue destination, String message) {
        this.jmsTemplate.convertAndSend(String.valueOf(destination),message);
    }
}
