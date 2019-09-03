package ru.kpfu.itis.auth.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.auth.repository.UserTokenRepository;

@Repository
public class UserTokenRepositoryRedisImpl implements UserTokenRepository {

    private static final String KEY = "user-token-expiration:";

    @Autowired
    private RedisTemplate<String, Long> redisTemplate;

    @Override
    public void save(String username, Long expiry) {
        String key = KEY + username;
        redisTemplate.opsForValue().set(key, expiry);
    }
}
