package ru.kpfu.itis.cat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CatServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatServiceApplication.class, args);
    }
}
