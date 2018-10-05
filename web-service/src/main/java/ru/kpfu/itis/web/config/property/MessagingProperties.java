package ru.kpfu.itis.web.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix="messaging")
public class MessagingProperties {

    private String exchange;
    private MessagingParams catCreation;
    private MessagingParams catReply;
    private MessagingParams user;

    @Data
    public static class MessagingParams {
        private String routingKey;
        private String queue;
    }
}
