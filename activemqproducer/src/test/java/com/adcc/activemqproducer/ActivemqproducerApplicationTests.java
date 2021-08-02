package com.adcc.activemqproducer;

import org.apache.activemq.command.ActiveMQQueue;
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
   //新建Queue:Firstqueue
        Destination destination=new ActiveMQQueue("Firstqueue");

        for(int i=0; i<5000; i++){
            producer.sendMessage(destination, "生产者发送了消息"+i);
            System.out.println("生产者发送了消息"+i);
            Thread.sleep(30000);
        }


    }

}
