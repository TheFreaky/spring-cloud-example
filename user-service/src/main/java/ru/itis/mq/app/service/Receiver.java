package ru.itis.mq.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.mq.app.dto.MessageDto;
import ru.itis.mq.app.model.Message;
import ru.itis.mq.app.repository.MessageRepository;

import java.io.IOException;

@Component
public class Receiver {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MessageRepository messageRepository;

    @RabbitListener(queues = "demo-queue")
    public void process(byte[] messageAsBytes) {
        String jsonMessage = new String(messageAsBytes);
        try {
            MessageDto messageDto = objectMapper.readValue(jsonMessage, MessageDto.class);
            Message message = Message.builder()
                    .email(messageDto.getEmail())
                    .message(messageDto.getMessage())
                    .name(messageDto.getName())
                    .phoneNumber(messageDto.getPhoneNumber())
                    .build();
            message = messageRepository.save(message);
            System.out.println(message);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
