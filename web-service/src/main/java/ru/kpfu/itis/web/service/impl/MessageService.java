package ru.kpfu.itis.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.web.config.property.MessagingProperties;
import ru.kpfu.itis.web.dto.CatDto;
import ru.kpfu.itis.web.dto.UserDto;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final SimpMessagingTemplate messagingTemplate;
    private final RabbitTemplate rabbitTemplate;
    private final MessagingProperties messagingProperties;

    public void getImage(String userId) {
        CatDto dto = CatDto.builder()
                .id(userId)
                .build();
        rabbitTemplate.convertAndSend(messagingProperties.getExchange(),
                messagingProperties.getCatCreation().getRoutingKey(), dto);
    }

    @RabbitListener(queues = "${messaging.cat-reply.queue}")
    public void sendImage(CatDto catDto) {
        String id = catDto.getId();
        messagingTemplate.convertAndSend("/topic/image/" + id + "/reply", catDto.getUrl());
    }

    public void registerUser(UserDto userDto) {
        rabbitTemplate.convertAndSend(messagingProperties.getExchange(),
                messagingProperties.getUser().getRoutingKey(), userDto);
    }
}
