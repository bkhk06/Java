package com.adcc.activemq;

import com.sun.jndi.ldap.pool.PooledConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

/**
 * Created by Liu.DA on 2019/9/3
 */
enum ActiveMqQueueEnum {
    /**
     * springboot-test-queue=测试Queue
     */
    TEST_QUEUE("springboot-test-queue", "测试Queue");

    private final String s;
    private final String 测试Queue;
    private String queueName;
    private String desc;

    public static final String testQueue = "springboot-test-queue";

    ActiveMqQueueEnum(String s, String 测试Queue) {

        this.s = s;
        this.测试Queue = 测试Queue;
    }

    public String getQueueName() {
    }
}
public class ActiveMqConfig {
    /**
     * The ActiveMQConnectionFactory creates ActiveMQ Connections.
     * The PooledConnectionFactory pools Connections.
     * If you only need to create one Connection and keep it around for a long time you don't need to pool.
     * If you tend to create many Connection instances over time then Pooling is better as connecting is a heavy operation and can be a performance bottleneck.
     * <p>
     * 可以在这里统一设置JmsTemplate的一些配置，也可以在具体使用到JmsTemplate的时候单独设置
     * JmsMessageTemplate是对JmsTemplate的进一步封装
     * TODO 目前看起来不起作用
     *
     * @param factory
     * @return
     */
    //    @Primary
//    @Bean
    public JmsTemplate jmsTemplate(PooledConnectionFactory factory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        //关闭事物
        jmsTemplate.setSessionTransacted(false);
        //TODO 在此设置无效
//        jmsTemplate.setSessionAcknowledgeMode(ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE);
        jmsTemplate.setConnectionFactory((ConnectionFactory) factory);
        return jmsTemplate;
    }

    @Bean(name = ActiveMqQueueEnum.testQueue)
    public ActiveMQQueue activeTestQueue() {
        return new ActiveMQQueue(ActiveMqQueueEnum.TEST_QUEUE.getQueueName());
    }
    /**
     * 定义一个消息监听器连接工厂，这里定义的是点对点模式的监听器连接工厂
     *
     * @param pooledConnectionFactory
     * @return
     */
    @Bean(name = "jmsQueueListener")
    public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory(PooledConnectionFactory pooledConnectionFactory) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory((ConnectionFactory) pooledConnectionFactory);
        factory.setSessionTransacted(false);
        factory.setSessionAcknowledgeMode(ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE);
        return factory;
    }
}
