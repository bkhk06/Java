package com.adcc.rabbitmq;
import com.adcc.rabbitmq.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制器
 *
 * @author young
 * @since 2018-03-21 15:25
 */
@RestController
@RequestMapping("/rabbit")
public class RabbitTest {

    @Autowired
    private Producer helloSender1;

    @PostMapping("/hello")
    public void hello() {
        helloSender1.send();
    }
}
