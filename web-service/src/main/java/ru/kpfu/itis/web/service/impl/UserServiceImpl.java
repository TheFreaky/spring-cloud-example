package ru.kpfu.itis.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.web.dto.UserDto;
import ru.kpfu.itis.web.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MessageService messageService;

    @Override
    public void registerUser(UserDto userDto) {
        messageService.registerUser(userDto);
    }

    @Override
    public List<UserDto> getAll() {
        //ToDo
        return null;
    }
}
