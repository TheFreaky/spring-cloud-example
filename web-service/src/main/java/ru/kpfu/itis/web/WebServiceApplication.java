package ru.kpfu.itis.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.kpfu.itis.web.config.property.MessagingProperties;

@SpringBootApplication
@EnableConfigurationProperties(MessagingProperties.class)
public class WebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class, args);
    }
}
