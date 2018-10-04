package ru.kpfu.itis.cat.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Value("${messaging.exchange}")
    String exchangeName;
    @Value("${messaging.queues.get}")
    String queueGetName;
    @Value("${messaging.queues.send}")
    String queueSendName;
    @Value("${messaging.routing-keys.get}")
    String routingKeyGetName;
    @Value("${messaging.routing-keys.send}")
    String routingKeySendName;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Exchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue queueGet() {
        return new Queue(queueGetName);
    }

    @Bean
    public Queue queueSend() {
        return new Queue(queueSendName);
    }

    @Bean
    public Binding bindingGet() {
        return BindingBuilder.bind(queueGet()).to(exchange()).with(routingKeyGetName).noargs();
    }

     @Bean
    public Binding bindingSend() {
        return BindingBuilder.bind(queueSend()).to(exchange()).with(routingKeySendName).noargs();
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
