README
### ǰ�˲���

#### `index.html`
����һ���򵥵�HTMLҳ�棬����һ��������һ����ť���ڷ�����Ϣ���Լ�һ���б�������ʾ��ʷ��Ϣ��

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Message App</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .container { width: 50%; margin: auto; }
        .message-box { margin-bottom: 20px; }
        #messageHistory { list-style-type: none; padding: 0; margin: 0; }
        #messageHistory li { margin: 5px 0; }
    </style>
</head>
<body>
<div class="container">
    <h1>Message App</h1>
    <div class="message-box">
        <input type="text" id="messageContent" placeholder="Enter your message here" required />
        <button type="button" onclick="sendMessage()">Send</button>
    </div>
    <div class="message-box">
        <h2>Message History</h2>
        <ul id="messageHistory"></ul>
    </div>
</div>
<script>
    function sendMessage() {
        const content = document.getElementById('messageContent').value;
        fetch('/api/messages/send', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(content)
        }).then(response => {
            if (response.ok) {
                loadMessageHistory();
                document.getElementById('messageContent').value = ''; // Clear input field
            } else {
                alert('Failed to send message');
            }
        });
    }

    function loadMessageHistory() {
        fetch('/api/messages/history')
            .then(response => response.json())
            .then(data => {
                const messageHistory = document.getElementById('messageHistory');
                messageHistory.innerHTML = ''; // Clear previous messages
                data.forEach(message => {
                    const li = document.createElement('li');
                    li.textContent = `${new Date(message.timestamp).toLocaleString()}: ${message.content}`;
                    messageHistory.appendChild(li);
                });
            });
    }

    window.onload = loadMessageHistory; // Load history when page loads
</script>
</body>
</html>
```

#### JavaScript�߼���
1. **`sendMessage`����**�����û������Send����ťʱ���ú����ᱻ���á�����������ȡ��Ϣ���ݣ�Ȼ��ͨ��`fetch` API���˷���һ��POST���󣬽���Ϣ���ݷ��͵�`/api/messages/send`�˵㡣�������ɹ����������`loadMessageHistory`������ˢ����Ϣ��ʷ��
   
2. **`loadMessageHistory`����**�����������ҳ�����ʱ��ÿ�η�����Ϣ�󱻵��á���ͨ��`fetch` API��`/api/messages/history`�˵��ȡ������Ϣ����ʷ��¼������������ʾ��ҳ���ϡ�

3. **`window.onload`�¼�**����ҳ��������ʱ���Զ�����`loadMessageHistory`�����Լ��س�ʼ����Ϣ��ʷ��

### ��˲���

#### `MessageController`��
����������ദ��ǰ�˷�����HTTP����

```java
package com.example.messageapp.controller;

import com.example.messageapp.entity.Message;
import com.example.messageapp.repository.MessageRepository;
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
```

#### �߼����ͣ�
1. **`sendMessage`����**���������ӳ�䵽`/api/messages/send`�˵㣬��ʹ��`@PostMapping`ע���ʾ����һ��POST����������һ���ַ������͵������壨����Ϣ���ݣ���Ȼ�����`messageService.sendMessage(content)`��������Ϣ���͵�RabbitMQ���С�
   
2. **`getHistory`����**���������ӳ�䵽`/api/messages/history`�˵㣬��ʹ��`@GetMapping`ע���ʾ����һ��GET�������������д洢��MySQL���ݿ��е���Ϣ��ʷ��¼��

### ������ʵ���

#### `MessageService`��
��������ฺ����ҵ���߼�����������Ϣ���͵�RabbitMQ���С�

```java
package com.example.messageapp.service;

import com.example.messageapp.entity.Message;
import com.example.messageapp.repository.MessageRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageRepository messageRepository;

    public void sendMessage(String content) {
        // Create a new message entity and save it to the database
        Message message = new Message();
        message.setContent(content);
        message.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
        messageRepository.save(message);
        
        // Send the message to RabbitMQ queue
        rabbitTemplate.convertAndSend("myQueue", content);
    }
}
```

#### �߼����ͣ�
1. **`sendMessage`����**�����ȴ���һ���µ���Ϣʵ�岢���䱣�浽MySQL���ݿ��С�Ȼ��������Ϣ���ݷ��͵�RabbitMQ���С�
   
2. **`RabbitTemplate`**������һ��Spring�ṩ��ģ���࣬���ڼ���RabbitMQ�Ľ��������������ʹ������`convertAndSend`��������Ϣ���͵���Ϊ`myQueue`�Ķ��С�

#### `Message`ʵ����
������ʾ���ݿ��е���Ϣ��

```java
package com.example.messageapp.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Timestamp timestamp;

    // Getters and setters omitted for brevity...
}
```

#### �߼����ͣ�
1. **�ֶ�**��`id`��������`content`����Ϣ���ݣ�`timestamp`����Ϣ��ʱ�����
2. **JPAע��**��`@Entity`��ʾ����һ��JPAʵ���࣬`@Id`��`@GeneratedValue`���ڱ�ʶ�����������ɲ��ԡ�

### �ܽ᣺
1. **ǰ��**���û�ͨ�������������Ϣ�������Send����ť��JavaScript�Ὣ��Ϣ���͵���˵�`/api/messages/send`�˵㡣���ͳɹ���ǰ�˻��Զ�ˢ����Ϣ��ʷ��
2. **���**����˽��յ���Ϣ�󣬽��䱣�浽MySQL���ݿ⣬��ͨ��RabbitMQ���͵���Ӧ�Ķ��С�ͬʱ��ǰ�˿���ͨ������`/api/messages/history`�˵��ȡ���µ���Ϣ��ʷ��