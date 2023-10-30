package com.osumed.chatapplication.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osumed.chatapplication.domain.Channel;
import com.osumed.chatapplication.domain.Message;
import com.osumed.chatapplication.domain.Person;

@Service
public class ChannelService {

	private final MessagesService messagesService;
	private final PersonService personService;
	private final ChannelService channelService;
	private final ChannelRepository ChannelRepository;

	private Integer messageNumber = 0;

	@Autowired
	public ChannelService(MessagesService messagesService, PersonService personService) {
		this.messagesService = messagesService;
		this.personService = personService;
	}

	public List<ArrayList<String>> getMessages(String channel_id) {
		Channel channelObject = channelService.getChannel(channel_id);
		List<Message> messages = messagesService.getMessages();

		List<ArrayList<String>> formattedMessages = messages.stream()
				.filter(message -> channel.equals(message.getChannel()))
				.map(message -> {
					ArrayList<String> tempList = new ArrayList<>();
					tempList.add(message.getPersonId());
					tempList.add(message.getMessage());
					System.out.println("The next : " + message);
					return tempList;
				})
				.collect(Collectors.toList());

		return formattedMessages;
	}

	private Channel getChannel(String channel_id) {

	}

	public void addMessage(Message message) {
		System.out.println("The new message is: " + message);
		messageNumber += 1;
		message.setMessageId(messageNumber);
		messagesService.addMessage(message);
		System.out.println("The new message is: " + message);
	}
}
