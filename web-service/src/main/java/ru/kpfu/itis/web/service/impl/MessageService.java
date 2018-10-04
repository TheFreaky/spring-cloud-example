package ru.kpfu.itis.web.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.web.dto.CatDto;
import ru.kpfu.itis.web.dto.UserDto;

@Service
public class MessageService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void getImage(String userId) {
        CatDto dto = CatDto.builder()
                .id(userId)
                .build();
        // ToDo: to property
        rabbitTemplate.convertAndSend("spring-cloud-example", "cat-get-event", dto);
    }

    @RabbitListener(queues = "cat-service-send")
    public void sendImage(CatDto catDto) {
        String id = catDto.getId();
        messagingTemplate.convertAndSend("/topic/image/" + id + "/reply", catDto.getUrl());
    }

    public void registerUser(UserDto userDto) {
        rabbitTemplate.convertAndSend("spring-cloud-example", "user-event", userDto);
    }
}
