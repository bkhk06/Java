package com.adcc.producer.config;

import javax.jms.Queue;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by Liu.DA on 2019/9/19
 */
@Configuration
public class BeanConfig {

    @Bean
    public Queue queue(){
        return new ActiveMQQueue("ActiveMQQueueTest");
    }
}
