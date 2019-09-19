package com.adcc.jmsdemo1;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by Liu.DA on 2019/9/19
 */
@Component
public class TopicSub {
    @JmsListener(destination = "topic.test"/*,containerFactory = "topicListenerContainerFactory"*/)
    /*,containerFactory = "topicListenerContainerFactory"*/
    public void receieve1(String msg){
        System.err.println("Topic.Subscribe.receive1接收消息："+msg);
    }

    @JmsListener(destination = "topic.test"/*,containerFactory = "topicListenerContainerFactory"*/)
    /*,containerFactory = "topicListenerContainerFactory"*/
    public void receieve2(String msg){
        System.err.println("Topic.Subscribe.receive2接收消息："+msg);
    }

    @JmsListener(destination = "topic.test"/*,containerFactory = "topicListenerContainerFactory"*/)
    /*,containerFactory = "topicListenerContainerFactory"*/
    public void receieve3(String msg){
        System.err.println("Topic.Subscribe.receive3接收消息："+msg);
    }

    @JmsListener(destination = "topic.test"/*,containerFactory = "topicListenerContainerFactory"*/)
    /*,containerFactory = "topicListenerContainerFactory"*/
    public void receieve4(String msg){
        System.err.println("Topic.Subscribe.receive4接收消息："+msg);
    }
}
