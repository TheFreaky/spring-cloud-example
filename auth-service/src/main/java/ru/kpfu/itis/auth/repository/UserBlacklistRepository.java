package ru.kpfu.itis.auth.repository;

public interface UserBlacklistRepository {
    boolean isPresent(String username);
}
