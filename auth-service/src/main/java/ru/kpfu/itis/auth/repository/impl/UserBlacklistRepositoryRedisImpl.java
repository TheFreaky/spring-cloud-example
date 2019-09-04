package ru.kpfu.itis.auth.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.auth.repository.UserBlacklistRepository;

@Repository
@RequiredArgsConstructor
public class UserBlacklistRepositoryRedisImpl implements UserBlacklistRepository {

    private static final String KEY = "user-blacklist:";

    @Autowired
    private final RedisTemplate<String, Long> redisTemplate;

    @Override
    public boolean isPresent(String username) {
        String key = KEY + username;
        Boolean hasKey = redisTemplate.hasKey(key);
        return hasKey != null && hasKey;
    }
}
