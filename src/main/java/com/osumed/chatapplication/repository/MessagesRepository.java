package com.osumed.chatapplication.repository;

import com.osumed.chatapplication.domain.Message;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class MessagesRepository {

    private List<Message> allMessages = new ArrayList<>();
    private Integer messageId = 0;

    public void save(Message message) {
        message.setMessageId(messageId);
        messageId++;
        allMessages.add(message);
    }

    public List<Message> findAll() {
        return allMessages;
    }

    public Integer findLastMessageId() {
        if (!allMessages.isEmpty()) {
            return allMessages.get(allMessages.size() - 1).getMessageId();
        }
        return null;
    }
}
