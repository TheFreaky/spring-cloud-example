package ru.kpfu.itis.cat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import ru.kpfu.itis.cat.config.property.MessagingProperties;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigurationProperties(MessagingProperties.class)
public class CatServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatServiceApplication.class, args);
    }
}
