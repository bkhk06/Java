package com.adcc.activemq;

import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by Liu.DA on 2019/9/18
 */
@Component
public class JMSConsumer {
    //private final static Logger logger = (Logger) LoggerFactory.getLogger(JMSConsumer.class);

    @JmsListener(destination = "springboot.queue.test")
    public void receieveQueue(String msg){
        System.out.println(msg);
        //logger.info(msg);
    }
}
