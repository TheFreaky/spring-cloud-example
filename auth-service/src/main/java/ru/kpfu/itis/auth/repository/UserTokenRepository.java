package ru.kpfu.itis.auth.repository;

public interface UserTokenRepository {
    void save(String username, Long expiry);
}
