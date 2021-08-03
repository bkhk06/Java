package com.adcc.jmsdemo1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by Liu.DA on 2019/9/19
 */
@Component
public class TopicConsumerListener {
    @Value("${topicName}")
    private static String topicName1;

    @Value("${topicName}")
    private static String topicName2;

    @Value("${topicName}")
    private static String topicName3;

    @Value("${topicName}")
    private static String topicName4;
    
    @JmsListener(destination ="${topicName1}",containerFactory = "topicListenerContainerFactory")
    /*,containerFactory = "topicListenerContainerFactory"*/
    public void receieve1(String msg){
        System.out.println("Topic.Subscribe.receive1接收消息："+msg);
    }

    @JmsListener(destination ="${topicName2}",containerFactory = "topicListenerContainerFactory")
    /*,containerFactory = "topicListenerContainerFactory"*/
    public void receieve2(String msg){
        System.out.println("Topic.Subscribe.receive2接收消息："+msg);
    }

    //@JmsListener(destination = topicName/*,containerFactory = "topicListenerContainerFactory"*/)
    /*,containerFactory = "topicListenerContainerFactory"*/
    @JmsListener(destination ="${topicName3}",containerFactory = "topicListenerContainerFactory")
    /*,containerFactory = "topicListenerContainerFactory"*/
    public void receieve3(String msg){
        System.out.println("Topic.Subscribe.receive3接收消息："+msg);
    }

    //@JmsListener(destination = topicName/*,containerFactory = "topicListenerContainerFactory"*/)
    /*,containerFactory = "topicListenerContainerFactory"*/
    @JmsListener(destination ="${topicName4}",containerFactory = "topicListenerContainerFactory")
    public void receieve4(String msg){
        System.out.println("Topic.Subscribe.receive4接收消息："+msg);
    }
}
