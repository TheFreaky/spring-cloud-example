package ru.kpfu.itis.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.user.model.User;
import ru.kpfu.itis.user.model.UserState;
import ru.kpfu.itis.user.repository.UserBlacklistRepository;
import ru.kpfu.itis.user.repository.UserRepository;
import ru.kpfu.itis.user.repository.UserTokenRepository;
import ru.kpfu.itis.user.service.AdminService;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserBlacklistRepository userBlacklistRepository;

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void banUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
        user.setState(UserState.BANNED);
        userRepository.save(user);

        Long lastLoginDate = userTokenRepository.get(username);
        userBlacklistRepository.save(username, new Date(lastLoginDate));
    }

    @Override
    @Transactional
    public void unbanUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
        user.setState(UserState.OK);
        userRepository.save(user);

        userBlacklistRepository.delete(username);
    }
}
