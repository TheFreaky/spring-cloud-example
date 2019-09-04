package ru.kpfu.itis.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kpfu.itis.web.dto.UserDto;
import ru.kpfu.itis.web.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final MessageService messageService;
    private final RestTemplate restTemplate;
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
        ResponseEntity<UserDto[]> userResponse = restTemplate.exchange(getAllUsersUrl, HttpMethod.GET, new HttpEntity<>(null, headers), UserDto[].class);
        return Arrays.asList(Objects.requireNonNull(userResponse.getBody()));
    }

    @Override
    public String loginUser(UserDto userDto) {
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(authUrl, userDto, Void.class);
        List<String> authorizationHeaderValues = responseEntity.getHeaders().get("Authorization");
        Optional<String> authorizationHeaderValue = Objects.requireNonNull(authorizationHeaderValues)
                .stream()
                .findFirst();
        return authorizationHeaderValue.orElseThrow(IllegalArgumentException::new);
    }
}
