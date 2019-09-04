package ru.kpfu.itis.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.user.dto.UserDto;
import ru.kpfu.itis.user.service.UserService;

@Component
@RequiredArgsConstructor
public class MessageConsumer {

    private final UserService userService;

    @RabbitListener(queues = "${messaging.user.queue}")
    public void process(UserDto dto) {
        userService.save(dto);
    }
}
