package com.osumed.chatapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osumed.chatapplication.domain.Message;
import com.osumed.chatapplication.repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@Service
public class MessagesService {

    private final MessageRepository messageRepository;

    private Integer messageNumber = 0;

    @Autowired
    public MessagesService(MessageRepository messageRepository) {
        this.messageRepository = new MessageRepository();
    }

    public void addMessage(Message message) {
        messageNumber += 1;
        message.setMessageId(messageNumber);
        messageRepository.addMessage(message); // Add the new message to the list
    }

    public List<Message> getMessages() {
        return messageRepository.getMessages();
    }

    public Integer getLastMessageId() {
        return messageRepository.getLastMessageId();
    }

}
