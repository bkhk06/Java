package com.adcc.activemqcomsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class Consumer {

    @JmsListener(destination = "${queueName}" ,containerFactory="queueListenerFactory")// 增加对应处理的监听器工程
    public void receiveQueue(TextMessage text) throws Exception {
        System.out.println(Thread.currentThread().getName()+":Consumer收到的Queue报文为:"+text.getText());
    }

    @JmsListener(destination="${topicName}", containerFactory="topicListenerFactory")// 增加对应处理的监听器工程
    public void receiveTopic(TextMessage text) throws JMSException{
        System.out.println(Thread.currentThread().getName()+":Consumer收到的Topic报文为:"+text.getText());
    }

}
