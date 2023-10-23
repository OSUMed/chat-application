package com.osumed.chatapplication.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osumed.chatapplication.domain.Person;

@Service
public class ChannelService {

    private final MessagesService messagesService;
    private final PersonService personService;

    @Autowired
    public ChannelService(MessagesService messagesService, PersonService personService) {
        this.messagesService = messagesService;
        this.personService = personService;
    }

	private List<ArrayList<String>> getMessages(){
		List<ArrayList<String>> messages = messagesService.getMessages();
		List<ArrayList<String>> formattedMessages = messages.stream()
			    .map(innerList -> {
			    		Long userId = Long.parseLong(innerList.get(1));
			    		Person person = personService.getPerson(userId);
			    		String username = person.getName();
		    		    innerList.set(1, username); // Corrected set method usage
			    	    return innerList;
			    		}
			    )
			    .collect(Collectors.toList());
		return formattedMessages;
	}

	private void addMessage(String message, Person person, String channel) {
		messagesService.addMessage(message, person.getPersonId(), channel);
	}
}
