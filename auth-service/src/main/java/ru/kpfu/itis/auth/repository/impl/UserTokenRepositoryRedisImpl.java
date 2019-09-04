package ru.kpfu.itis.auth.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.auth.repository.UserTokenRepository;

@Repository
@RequiredArgsConstructor
public class UserTokenRepositoryRedisImpl implements UserTokenRepository {

    private static final String KEY = "user-token-expiration:";

    private final RedisTemplate<String, Long> redisTemplate;

    @Override
    public void save(String username, Long expiry) {
        String key = KEY + username;
        redisTemplate.opsForValue().set(key, expiry);
    }
}
