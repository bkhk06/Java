package com.adcc.rmqdemo1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Rmqdemo1Application.class) // 测试的主类名称
public class FanoutTestController {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FanoutMessageSender messageSender;

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        messageSender.sendMessage(message);
        return "Message sent: " + message;
    }

}
