package com.adcc.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Liu.DA on 2019/9/3
 */
@RestController
public class TopicConsumerController {
    /*
     * 监听和读取消息
     */
    @JmsListener(destination="active.topic")
    public void readActiveTopic(String message) {
        System.out.println("接受到2：" + message);
        //TODO something
    }
}
