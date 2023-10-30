package com.osumed.chatapplication.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.osumed.chatapplication.domain.Message;
import com.osumed.chatapplication.domain.Person;
import com.osumed.chatapplication.services.PersonService;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("")
    public String getMessages() {
        return "Hello";
    }

    @PostMapping("")
    public Person postUser(@RequestBody String username) {
        System.out.println(username);
        Person returnedPerson = personService.addPerson(username);
        return returnedPerson;
    }
}
