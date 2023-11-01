package com.osumed.chatapplication.services;

import org.springframework.stereotype.Service;

import com.osumed.chatapplication.domain.Message;
import com.osumed.chatapplication.domain.MessageDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesService {
    List<Message> allMessages = new ArrayList<Message>();
    private Integer messageId = 0;

    public void addMessage(Message message) {
        allMessages.add(message); // Add the new message to the list
    }

    public List<Message> getMessages() {
        return allMessages;
    }

    public Integer getLastMessageId() {
        return allMessages.get(allMessages.size() - 1).getMessageId();
    }

    public Message convertDTOToMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setMessageId(messageId);
        messageId++;
        message.setUserId(Integer.parseInt(messageDTO.getUserId()));
        message.setChannelId(Integer.parseInt(messageDTO.getChannelId()));
        message.setMessage(messageDTO.getMessage());
        System.out.println("The created message is: " + message);
        return message;
    }
}
