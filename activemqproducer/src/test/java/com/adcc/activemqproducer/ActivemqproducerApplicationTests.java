package com.adcc.activemqproducer;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ActivemqproducerApplication.class)
//@Component
//@ConfigurationProperties(prefix = "mqmsg")
public class ActivemqproducerApplicationTests {

    @Autowired
    private Producer producer;

    @Value("${queueMsg}")
    private String queueMsg;

    @Value("${topicMsg}")
    private String topicMsg;

    @Value("${queueName}")
    private  String queueName;

    @Value("${topicName}")
    private String topicName;

    @Value("${intervalTime}")
    private int intervalTime;

    @Test
    public void contextLoads() throws InterruptedException {
        //创建测试Queue和Topic
        Destination queue_test=new ActiveMQQueue(queueName);
        Destination topic_test=new ActiveMQTopic(topicName);

        for(int i=1; i<5000; i++){
            System.out.println(i+"生产者发送了Flight Queue消息"+queueMsg);
            producer.sendMessage(queue_test, queueMsg);

            Thread.sleep(3000);
            System.out.println(i+"生产者发送了Flow Control Queue消息"+topicMsg);
            producer.sendMessage(queue_test, topicMsg);

            Thread.sleep(3000);
            System.out.println(i+"生产者发送了Topic消息"+topicMsg);
            producer.sendMessage(topic_test,topicMsg);
            //
            Thread.sleep(intervalTime);
        }


    }

}
