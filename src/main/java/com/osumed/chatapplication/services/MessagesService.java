package com.osumed.chatapplication.services;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@Service
public class MessagesService {
	List<ArrayList<String>> allMessages = new ArrayList<ArrayList<String>>();
    
    public void addMessage(String message, Long userId, String channel) {
        ArrayList<String> newMessage = new ArrayList<>(Arrays.asList(message, channel, String.valueOf(userId)));
        allMessages.add(newMessage); // Add the new message to the list
    }
    
    public List<ArrayList<String>> getMessages(){
    	return allMessages;
    }
}
