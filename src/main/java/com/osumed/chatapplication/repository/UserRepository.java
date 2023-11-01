package com.osumed.chatapplication.repository;

import com.osumed.chatapplication.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Repository
public class UserRepository {

    private final HashMap<Integer, User> users = new HashMap<>();

    public Optional<User> getUser(Integer userId) {
        return Optional.ofNullable(users.get(userId));
    }

    public User addUser(User user) {
        users.put(user.getUserId(), user);
        return user;
    }
}
