package ru.kpfu.itis.cat.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.cat.dto.CatDto;
import ru.kpfu.itis.cat.service.CatService;

@Component
public class MessageService {

    @Autowired
    private CatService catService;

    @RabbitListener(queues = "${messaging.queue}")
    public String process() {
        return catService.getCat()
                .map(CatDto::getUrl)
                .orElseThrow(IllegalArgumentException::new);
    }
}
