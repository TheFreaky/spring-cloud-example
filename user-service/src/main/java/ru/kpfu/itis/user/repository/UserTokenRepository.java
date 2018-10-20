package ru.kpfu.itis.user.repository;

public interface UserTokenRepository {
    Long get(String username);
}
