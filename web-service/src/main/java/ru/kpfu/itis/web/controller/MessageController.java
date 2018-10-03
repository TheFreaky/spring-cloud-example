package ru.kpfu.itis.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;

@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/image.{userId}")
    public void getImage(Message<?> message, @DestinationVariable("userId") String userId) {
        messageService.getImage();
    }

    //ToDO: MQ listener
    // В js генерится UUID, подписывается на /image.{userId}/reply и посылается запрос на /image.{userId}
    //http://qaru.site/questions/133705/where-user-comes-from-in-convertandsendtouser-works-in-sockjsspring-websocket
    //https://habr.com/post/187822/
    //Унести в service
    @SendTo("/topic/web")
    public TextMessage sendImage() {
        messagingTemplate.convertAndSend();
    }
}
