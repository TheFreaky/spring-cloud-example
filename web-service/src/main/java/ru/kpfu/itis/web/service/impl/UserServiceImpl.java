package ru.kpfu.itis.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kpfu.itis.web.dto.UserDto;
import ru.kpfu.itis.web.service.UserService;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MessageService messageService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void registerUser(UserDto userDto) {
        messageService.registerUser(userDto);
    }

    @Override
    public List<UserDto> getAll() {
        String zuulUrl = "http://localhost:8090";
        String getAllUserPath = "/api/users";
//        restTemplate.getForEntity()
        return Arrays.asList(restTemplate.getForObject(zuulUrl + getAllUserPath, UserDto[].class));
    }
}
