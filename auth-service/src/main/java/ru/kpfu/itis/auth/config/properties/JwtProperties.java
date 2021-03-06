package ru.kpfu.itis.auth.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class JwtProperties {
    @Value("${security.jwt.url:/auth/**}")
    private String url;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.expiration:#{24*60*60}}")
    private int expiration;

    @Value("${security.jwt.secret:JwtSecretKey}")
    private String secret;
}
