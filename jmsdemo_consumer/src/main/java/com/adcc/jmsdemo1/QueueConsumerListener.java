package com.adcc.jmsdemo1;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by Liu.DA on 2019/9/19
 */
@Component
public class QueueConsumerListener {

    @JmsListener(destination = "${queueName}")
    public void receieve(String msg){
        System.err.println("Queue Consumer receieved messages: "+msg);
    }
}
