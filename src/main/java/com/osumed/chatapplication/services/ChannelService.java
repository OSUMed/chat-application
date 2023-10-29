package com.osumed.chatapplication.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osumed.chatapplication.domain.Message;
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

	public List<ArrayList<String>> getMessages(String channel) {
		List<Message> messages = messagesService.getMessages();

		List<ArrayList<String>> formattedMessages = messages.stream()
				.filter(message -> channel.equals(message.getChannel()))
				.map(message -> {
					ArrayList<String> tempList = new ArrayList<>();
					tempList.add(message.getPersonId());
					tempList.add(message.getMessage());
					return tempList;
				})
				.collect(Collectors.toList());

		return formattedMessages;
	}

	public void addMessage(Message message) {
		messagesService.addMessage(message);
	}
}
