package com.adcc.rmqdemo1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Rmqdemo1Application.class) // 测试的主类名称
public class Rabbitmqdemo2ApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate; // 使用RabbitTemplate,这提供了接收/发送等等方法

    @Value("${intervalTime}")
    private int intervalTime;

    //@Value("${messageData}")
    private String messageData="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><MSG><HEAD><MESSAGESENDDATETIME>20191220080009</MESSAGESENDDATETIME><MESSAGESEQUENCE>643af47b9043465db26d14d76ef1d25a</MESSAGESEQUENCE><MESSAGETYPE>ATMI</MESSAGETYPE><MESSAGESUBTYPE>FCAI</MESSAGESUBTYPE><SOURCESYSTEMID>ZUUUCDM</SOURCESYSTEMID><DESTINATIONSYSTEMID>ZUUUEFPS</DESTINATIONSYSTEMID></HEAD><BODY><ITEM><ID>1777223</ID><NAME>IDEPO ZGSZ,ZGSD,VMMC 限制间隔 10分钟</NAME><START_TIME>201912200800</START_TIME><END_TIME>201912200930</END_TIME><REASON>MILITARY</REASON><REMARK></REMARK><PUBLISH_USER>成都-流量管理室</PUBLISH_USER><PUBLISH_UNIT>流量室</PUBLISH_UNIT><SOURCE>CRS</SOURCE><LONG_TERM>0</LONG_TERM><STATUS>FUTURE</STATUS><CONTROL_DIRECTION><POINT>IDEPO</POINT><INCLUDE_DEPAP></INCLUDE_DEPAP><EXEMPT_DEPAP></EXEMPT_DEPAP><INCLUDE_ARRAP>ZGSZ,ZGSD,VMMC</INCLUDE_ARRAP><EXEMPT_ARRAP></EXEMPT_ARRAP><INCLUDE_ACTYPE></INCLUDE_ACTYPE><EXEMPT_ACTYPE></EXEMPT_ACTYPE></CONTROL_DIRECTION><CONTROL_PROGRAM><TYPE>TIME</TYPE><VALUE>10</VALUE><ASSIGN_SLOT></ASSIGN_SLOT></CONTROL_PROGRAM></ITEM><ITEM><ID>1776903</ID><NAME>20日 LLC ZSWZ,ZSLQ 限制间隔 10分钟</NAME><START_TIME>201912200641</START_TIME><END_TIME>201912200841</END_TIME><REASON>CONTROL</REASON><REMARK></REMARK><PUBLISH_USER>成都-流量管理室</PUBLISH_USER><PUBLISH_UNIT>流量室</PUBLISH_UNIT><SOURCE>CRS</SOURCE><LONG_TERM>0</LONG_TERM><STATUS>RUNNING</STATUS><CONTROL_DIRECTION><POINT>LLC</POINT><INCLUDE_DEPAP></INCLUDE_DEPAP><EXEMPT_DEPAP></EXEMPT_DEPAP><INCLUDE_ARRAP>ZSWZ,ZSLQ</INCLUDE_ARRAP><EXEMPT_ARRAP></EXEMPT_ARRAP><INCLUDE_ACTYPE></INCLUDE_ACTYPE><EXEMPT_ACTYPE></EXEMPT_ACTYPE></CONTROL_DIRECTION><CONTROL_PROGRAM><TYPE>TIME</TYPE><VALUE>10</VALUE><ASSIGN_SLOT></ASSIGN_SLOT></CONTROL_PROGRAM></ITEM><ITEM><ID>1776965</ID><NAME>20日 AGULU ZSDY,ZSJN,ZSWF 限制间隔 40分钟</NAME><START_TIME>201912200655</START_TIME><END_TIME>201912201535</END_TIME><REASON>MILITARY</REASON><REMARK></REMARK><PUBLISH_USER>成都-流量管理室</PUBLISH_USER><PUBLISH_UNIT>流量室</PUBLISH_UNIT><SOURCE>CRS</SOURCE><LONG_TERM>0</LONG_TERM><STATUS>RUNNING</STATUS><CONTROL_DIRECTION><POINT>AGULU,SUBUL</POINT><INCLUDE_DEPAP></INCLUDE_DEPAP><EXEMPT_DEPAP></EXEMPT_DEPAP><INCLUDE_ARRAP>ZSDY,ZSJN,ZSWF</INCLUDE_ARRAP><EXEMPT_ARRAP></EXEMPT_ARRAP><INCLUDE_ACTYPE></INCLUDE_ACTYPE><EXEMPT_ACTYPE></EXEMPT_ACTYPE></CONTROL_DIRECTION><CONTROL_PROGRAM><TYPE>TIME</TYPE><VALUE>40</VALUE><ASSIGN_SLOT></ASSIGN_SLOT></CONTROL_PROGRAM></ITEM></BODY></MSG>\r\n";

    @Test
    void contextLoads() throws InterruptedException {
        // 创建测试
        for (int i = 1; i < 500000; i++) {
            String messageId = String.valueOf(UUID.randomUUID());
            // String messageData = "test message, hello!";
            String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            // Map<String,Object> map=new HashMap<>();
            String msg_temp=null;
            msg_temp = "创建时间：" + createTime + ", " + messageId + ", " + messageData;
            /*
             * map.put("messageId",messageId);
             * map.put("messageData",messageData);
             * map.put("createTime",createTime);
             */
            // 将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange

            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setContentType("text/plain");
            messageProperties.setContentEncoding("UTF-8");
            Message msg = new Message(msg_temp.getBytes(), messageProperties);

            System.out.println("++++ " + i + " ======>>>>>>>>Messages are sent: " + msg);
            rabbitTemplate.convertAndSend("hmall.fanout", "", msg);
            Thread.sleep(intervalTime);
        }
    }

}