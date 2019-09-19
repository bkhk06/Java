package com.adcc.producer;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;
import javax.jms.JMSProducer;

@RunWith(Parameterized.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProducerApplicationTests extends BaseTest {

    @Autowired
    private JMSProducer jmsProducer;

    public ProducerApplicationTests(JMSProducer jmsProducer) {
        this.jmsProducer = jmsProducer;
    }

    @Test
    public void testJms(){
        Destination destination=new ActiveMQQueue("ActiveMQQueueTest");

        for (int i=0;i<10;i++){
            jmsProducer.send(destination,"hello");
        }
    }
}
