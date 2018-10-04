package ru.kpfu.itis.web.service;

import ru.kpfu.itis.web.dto.UserDto;

import java.util.List;

public interface UserService {
    void registerUser(UserDto userDto);

    List<UserDto> getAll();
}
