package ru.kpfu.itis.user.repository;

import java.util.Date;

public interface UserBlacklistRepository {
    void save(String username, Date expiry);

    void delete(String username);
}
