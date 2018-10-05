package ru.kpfu.itis.user.service;

import ru.kpfu.itis.user.dto.UserDto;
import ru.kpfu.itis.user.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    void save(UserDto dto);
}
