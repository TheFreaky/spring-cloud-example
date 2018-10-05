package ru.kpfu.itis.cat.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix="messaging")
public class MessagingProperties {

    private String exchange;
    private MessagingParams catCreation;
    private MessagingParams catReply;

    @Data
    public static class MessagingParams {
        private String queue;
        private String routingKey;
    }
}
