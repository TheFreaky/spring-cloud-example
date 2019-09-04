package ru.kpfu.itis.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import ru.kpfu.itis.web.service.impl.MessageService;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @MessageMapping("/image/{userId}")
    public void sendEventToCreateCatImage(@DestinationVariable("userId") String userId) {
        messageService.getImage(userId);
    }
}
