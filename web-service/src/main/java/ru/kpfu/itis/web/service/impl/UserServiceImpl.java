package ru.kpfu.itis.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
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

    @Value("${auth.url}")
    private String authUrl;

    @Override
    public void registerUser(UserDto userDto) {
        messageService.registerUser(userDto);
    }

    @Override
    public List<UserDto> getAll(String authHeaderValue) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authHeaderValue);
        ResponseEntity<UserDto[]> userResponse = restTemplate.exchange(getAllUsersUrl, HttpMethod.GET, new HttpEntity<>(headers.toSingleValueMap()), UserDto[].class);
        return Arrays.asList(userResponse.getBody());
    }

    @Override
    public String loginUser(UserDto userDto) {
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(authUrl, userDto, Void.class);
        return responseEntity.getHeaders().get("Authorization").stream().findFirst().get();
    }
}
