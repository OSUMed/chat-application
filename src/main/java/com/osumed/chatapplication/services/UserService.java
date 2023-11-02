package com.osumed.chatapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.osumed.chatapplication.domain.User;
import com.osumed.chatapplication.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private Integer userId = 0;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUser(Integer userId) {
        return userRepository.getUser(userId);
    }

    public User addUser(String name) {
        User user = new User(name, userId);
        userId++;
        return userRepository.addUser(user);
    }
}
