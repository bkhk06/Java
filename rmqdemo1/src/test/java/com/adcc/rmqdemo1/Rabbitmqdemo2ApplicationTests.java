package com.adcc.rmqdemo1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Rmqdemo1Application.class)  //测试的主类名称
public class Rabbitmqdemo2ApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @Value("${intervalTime}")
    private int intervalTime;

    @Value("${messageData}")
    private String messageData;

    @Test
    void contextLoads() throws InterruptedException {
        //创建测试
        for(int i=1; i<500000; i++){
            String messageId = String.valueOf(UUID.randomUUID());
            //String messageData = "test message, hello!";
            String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Map<String,Object> map=new HashMap<>();
            map.put("messageId",messageId);
            map.put("messageData",messageData);
            map.put("createTime",createTime);
            //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
            System.out.println("++++ "+i+" ======>>>>>>>>Messages are sent: "+map);
            rabbitTemplate.convertAndSend("topicExchangeAdcc", "topicExchangeAdcc", map);
            Thread.sleep(intervalTime);
        }
    }

}