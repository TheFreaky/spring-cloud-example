package ru.kpfu.itis.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.user.dto.UserDto;
import ru.kpfu.itis.user.model.User;
import ru.kpfu.itis.user.repository.UserRepository;
import ru.kpfu.itis.user.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(UserDto userDto) {
        User user = User.builder()
                .imgUrl(userDto.getImgUrl())
                .name(userDto.getName())
                .build();
        userRepository.save(user);
    }
}
