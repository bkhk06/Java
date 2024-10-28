package com.adcc.msgdemo;

import java.time.LocalDateTime;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageService {
    private static final String QUEUE_NAME = "messageQueue";

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private MessageRepository messageRepository;

    public void sendMessage(String content) {
        rabbitTemplate.convertAndSend(QUEUE_NAME, content);
    }

    @RabbitListener(queues = QUEUE_NAME)
    @Transactional
    public void receiveMessage(String content) {
        Message message = new Message();
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        messageRepository.save(message);
    }
}

