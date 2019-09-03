package ru.kpfu.itis.auth.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.auth.repository.UserBlacklistRepository;

@Repository
public class UserBlacklistRepositoryRedisImpl implements UserBlacklistRepository {

    private static final String KEY = "user-blacklist:";

    @Autowired
    private RedisTemplate<String, Long> redisTemplate;

    @Override
    public boolean isPresent(String username) {
        String key = KEY + username;
        return redisTemplate.hasKey(key);
    }
}
