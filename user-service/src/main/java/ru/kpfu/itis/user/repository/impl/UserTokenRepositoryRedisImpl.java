package ru.kpfu.itis.user.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.user.repository.UserTokenRepository;

@Repository
public class UserTokenRepositoryRedisImpl implements UserTokenRepository {

    private static final String KEY = "user-token-expiration:";

    @Autowired
    private RedisTemplate<String, Long> redisTemplate;

    @Override
    public Long get(String username) {
        String key = KEY + username;
        return redisTemplate.opsForValue().get(key);
    }
}
