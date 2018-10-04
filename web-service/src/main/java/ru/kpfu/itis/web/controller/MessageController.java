package ru.kpfu.itis.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import ru.kpfu.itis.web.service.impl.MessageService;

@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;

    @MessageMapping("/image/{userId}")
    public void getImage(Message<?> message, @DestinationVariable("userId") String userId) {
        messageService.getImage(userId);
    }
}
