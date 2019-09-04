package ru.kpfu.itis.user.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.user.repository.UserTokenRepository;

@Repository
@RequiredArgsConstructor
public class UserTokenRepositoryRedisImpl implements UserTokenRepository {

    private static final String KEY = "user-token-expiration:";

    private final RedisTemplate<String, Long> redisTemplate;

    @Override
    public Long get(String username) {
        String key = KEY + username;
        return redisTemplate.opsForValue().get(key);
    }
}
