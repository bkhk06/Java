package com.adcc.msgdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("/send")
    public void sendMessage(@RequestBody String content) {
        messageService.sendMessage(content);
    }

    @GetMapping("/history")
    public List<Message> getHistory() {
        return messageRepository.findAll();
    }
}
