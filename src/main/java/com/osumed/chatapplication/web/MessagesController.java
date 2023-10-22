package com.osumed.chatapplication.web;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessagesController {

    @GetMapping("/")
    public String getMessages() {
        return "Hello World!";
    }
}
