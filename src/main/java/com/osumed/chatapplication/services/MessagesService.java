package com.osumed.chatapplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osumed.chatapplication.domain.Message;
import com.osumed.chatapplication.domain.MessageDTO;
import com.osumed.chatapplication.repository.MessagesRepository;

@Service
public class MessagesService {

    @Autowired
    private MessagesRepository messagesRepository;

    public void addMessage(Message message) {
        messagesRepository.save(message);
    }

    public List<Message> getMessages() {
        return messagesRepository.findAll();
    }

    public Integer getLastMessageId() {
        return messagesRepository.findLastMessageId();
    }

    public Message convertDTOToMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setUserId(Integer.parseInt(messageDTO.getUserId()));
        message.setChannelId(Integer.parseInt(messageDTO.getChannelId()));
        message.setMessage(messageDTO.getMessage());
        return message;
    }
}
