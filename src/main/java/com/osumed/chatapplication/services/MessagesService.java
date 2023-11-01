package com.osumed.chatapplication.services;

import org.springframework.stereotype.Service;

import com.osumed.chatapplication.domain.Message;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesService {
    List<Message> allMessages = new ArrayList<Message>();

    public void addMessage(Message message) {
        allMessages.add(message); // Add the new message to the list
    }

    public List<Message> getMessages() {
        return allMessages;
    }

    public Integer getLastMessageId() {
        return allMessages.get(allMessages.size() - 1).getMessageId();
    }
}
