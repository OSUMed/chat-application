package com.osumed.chatapplication.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/")
    public String getMessages() {
        return "Hello";
    }

    @PostMapping("")
    public User postUser(@RequestBody String username) {
        System.out.println(username);
        User returnedPerson = userService.addUser(username);
        return returnedPerson;
    }
}