package ru.kpfu.itis.cat.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.cat.dto.CatDto;
import ru.kpfu.itis.cat.service.CatService;

@Component
public class MessageService {

    @Autowired
    private CatService catService;

    @Value("${messaging.exchange}")
    String exchangeName;
    @Value("${messaging.routing-keys.send}")
    String routingKeySendName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "${messaging.queues.get}")
    public void process(CatDto dto) {
        CatDto catDto = catService.getCat()
                .orElseThrow(IllegalArgumentException::new);
        catDto.setId(dto.getId());

        rabbitTemplate.convertAndSend(exchangeName, routingKeySendName, catDto);
    }
}
