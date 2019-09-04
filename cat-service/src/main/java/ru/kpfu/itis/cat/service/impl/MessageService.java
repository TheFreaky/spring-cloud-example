package ru.kpfu.itis.cat.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.cat.config.property.MessagingProperties;
import ru.kpfu.itis.cat.dto.CatDto;
import ru.kpfu.itis.cat.service.CatService;

@Component
@RequiredArgsConstructor
public class MessageService {

    private final CatService catService;
    private final MessagingProperties messagingProperties;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "${messaging.cat-creation.queue}")
    public void process(CatDto dto) {
        CatDto catDto = catService.getCat()
                .orElseThrow(IllegalArgumentException::new);
        catDto.setId(dto.getId());

        rabbitTemplate.convertAndSend(messagingProperties.getExchange(),
                messagingProperties.getCatReply().getRoutingKey(), catDto);
    }
}
