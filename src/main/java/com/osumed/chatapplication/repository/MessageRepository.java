package com.osumed.chatapplication.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.osumed.chatapplication.domain.Message;

@Repository
public class MessageRepository {

    private List<Message> allMessages;

    public MessageRepository() {
        this.allMessages = new ArrayList<Message>();
    }

    public void addMessage(Message message) {
        allMessages.add(message);
    }

    public List<Message> getMessages() {
        return allMessages;
    }

    public Integer getLastMessageId() {
        return allMessages.get(allMessages.size() - 1).getMessageId();
    }
}
