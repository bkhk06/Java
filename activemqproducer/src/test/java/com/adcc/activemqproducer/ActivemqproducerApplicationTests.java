package com.adcc.activemqproducer;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ActivemqproducerApplication.class)
public class ActivemqproducerApplicationTests {

    @Autowired
    private Producer producer;

    @Test
    public void contextLoads() throws InterruptedException {
        //创建测试Queue和Topic
        Destination queue_test=new ActiveMQQueue("QueueTest");
        Destination topic_test=new ActiveMQTopic("TopicTest");


        for(int i=0; i<5000; i++){
            producer.sendMessage(queue_test, "生产者发送了Queue消息"+i);
            System.out.println("生产者发送了Queue消息"+i);
            producer.sendMessage(topic_test,"生产者发送了Topic消息"+i);
            System.out.println("生产者发送了Topic消息"+i);
            Thread.sleep(30000);
        }


    }

}
