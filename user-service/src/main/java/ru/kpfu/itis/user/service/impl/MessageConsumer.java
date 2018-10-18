package ru.kpfu.itis.user.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.user.dto.UserDto;
import ru.kpfu.itis.user.service.UserService;

@Component
public class MessageConsumer {

    @Autowired
    private UserService userService;

    @RabbitListener(queues = "${messaging.user.queue}")
    public void process(UserDto dto) {
        userService.save(dto);
    }
}
