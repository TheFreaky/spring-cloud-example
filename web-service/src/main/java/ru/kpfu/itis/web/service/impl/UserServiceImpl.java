package ru.kpfu.itis.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${user.get-all.url}")
    private String getAllUsersUrl;

    @Override
    public void registerUser(UserDto userDto) {
        messageService.registerUser(userDto);
    }

    @Override
    public List<UserDto> getAll() {
        return Arrays.asList(restTemplate.getForObject(getAllUsersUrl, UserDto[].class));
    }
}
