package ru.kpfu.itis.user.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.user.repository.UserBlacklistRepository;

import java.util.Date;

@Repository
public class UserBlacklistRepositoryRedisImpl implements UserBlacklistRepository {

    private static final String KEY = "user-blacklist:";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void save(String username, Date expiry) {
        String key = KEY + username;
        redisTemplate.opsForValue().set(key, "");
        redisTemplate.expireAt(key, expiry);
    }

    @Override
    public void delete(String username) {
        String key = KEY + username;
        redisTemplate.delete(key);
    }
}
