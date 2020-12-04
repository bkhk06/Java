package com.adcc.jmsdemo1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * Created by Liu.DA on 2019/9/19
 */
@Component
public class TopicConsumerListener {
    @Value("${topicName}")
    private static String topicName;
    
    @JmsListener(destination ="${topicName}",containerFactory = "topicListenerContainerFactory")
    /*,containerFactory = "topicListenerContainerFactory"*/
    public void receieve1(String msg){

        System.out.println("The receieved msg format is:"+getEncoding(msg));

        System.err.println("Topic.${topicName}.Subscribe.receive 接收消息："+msg);
    }

    public String getEncoding(String str) {

        String encoding = "GBK";

        try {

            if (str.equals(new String(str.getBytes(), encoding))) {

                return encoding;
            }

        } catch (UnsupportedEncodingException e) {

// TODO Auto-generated catch block e.printStackTrace();

        }

        encoding = "UTF-8";

        try {

            if (str.equals(new String(str.getBytes(), encoding))) {

                return encoding;
            }
        } catch (UnsupportedEncodingException e) {

// TODO Auto-generated catch block e.printStackTrace();

        }

        encoding = "ISO-8859-1";

        try {

            if (str.equals(new String(str.getBytes(), encoding))) {

                return encoding;

            }

        } catch (UnsupportedEncodingException e) {

// TODO Auto-generated catch block e.printStackTrace();

        }

        encoding = "GB2312";

        try {

            if (str.equals(new String(str.getBytes(), encoding))) {

                return encoding;

            }

        } catch (UnsupportedEncodingException e) {

// TODO Auto-generated catch block e.printStackTrace();

        }

        return null;

    }


}
