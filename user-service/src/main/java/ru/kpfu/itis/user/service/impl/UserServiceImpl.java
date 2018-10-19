package ru.kpfu.itis.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.user.dto.UserDto;
import ru.kpfu.itis.user.model.User;
import ru.kpfu.itis.user.repository.UserRepository;
import ru.kpfu.itis.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAll() {
        return userRepository.findAll().stream()
                .peek(user -> user.setPassword(null))
                .collect(Collectors.toList());
    }

    @Override
    public void save(UserDto userDto) {
        User user = User.builder()
                .imgUrl(userDto.getImgUrl())
                .name(userDto.getName())
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
        userRepository.save(user);
    }
}
