package com.osumed.chatapplication.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osumed.chatapplication.domain.User;
import com.osumed.chatapplication.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("")
    public User postUser(@RequestBody String username) {
        User returnedPerson = userService.addUser(username);
        return returnedPerson;
    }
}
