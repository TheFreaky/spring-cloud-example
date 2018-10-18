package ru.kpfu.itis.cat.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kpfu.itis.cat.config.property.MessagingProperties;

@Configuration
public class MessagingConfig {

    @Autowired
    private MessagingProperties messagingProperties;

    @Bean
    public Exchange exchange() {
        return new TopicExchange(messagingProperties.getExchange());
    }

    @Bean
    public Queue queueGet() {
        return new Queue(messagingProperties.getCatCreation().getQueue());
    }

    @Bean
    public Queue queueSend() {
        return new Queue(messagingProperties.getCatReply().getQueue());
    }

    @Bean
    public Binding bindingGet() {
        return BindingBuilder.bind(queueGet()).to(exchange()).with(messagingProperties.getCatCreation().getRoutingKey()).noargs();
    }

    @Bean
    public Binding bindingSend() {
        return BindingBuilder.bind(queueSend()).to(exchange()).with(messagingProperties.getCatReply().getRoutingKey()).noargs();
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
