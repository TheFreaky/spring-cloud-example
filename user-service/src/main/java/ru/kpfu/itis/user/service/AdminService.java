package ru.kpfu.itis.user.service;

public interface AdminService {
    void banUser(String username);

    void unbanUser(String username);
}
