package com.adcc.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.Destination;

/**
 * Created by Liu.DA on 2019/9/19
 */
@RestController
public class Controller {
    //注入存放消息的队列，用于下列方法一
    @Autowired
    private Queue queue;

    //注入springboot封装的工具类
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("send")
    public void send(String msg){
        //方法一：添加消息到消息队列
        jmsMessagingTemplate.convertAndSend(queue,msg);
        //方法二：这种方式不需要手动创建queue，系统会自行创建名为test的队列
        //jmsMessagingTemplate.convertAndSend("test", name);
    }
}
