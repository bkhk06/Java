package com.adcc.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivemqApplicationTests {
    @Autowired
    private JMSProducer jmsProducer;

    @Test
    public void testJms(){
        ActiveMQQueue destination = new ActiveMQQueue("springboot.queue.test");
        for (int i=0;i<10;i++) {
            jmsProducer.sendMessage(destination,"hello,world!" + i);
        }
    }

    }

