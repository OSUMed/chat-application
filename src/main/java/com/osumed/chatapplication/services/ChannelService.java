package com.osumed.chatapplication.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osumed.chatapplication.domain.Channel;
import com.osumed.chatapplication.domain.Message;
import com.osumed.chatapplication.domain.Person;
import com.osumed.chatapplication.repository.ChannelRepository;

@Service
public class ChannelService {

	private final MessagesService messagesService;
	private final PersonService personService;
	private final ChannelRepository channelRepository;

	@Autowired
	public ChannelService(MessagesService messagesService, PersonService personService,
			ChannelRepository channelRepository) {
		this.messagesService = messagesService;
		this.personService = personService;
		this.channelRepository = channelRepository;
	}

	public List<ArrayList<String>> getMessagesFromChannel(String channel_id) {
		Channel channelObject = getChannel(channel_id);
		List<Message> messages = messagesService.getMessages();

		List<ArrayList<String>> formattedMessages = messages.stream()
				.filter(message -> channelObject.equals(getChannel(message.getChannelId())))
				.map(message -> {
					ArrayList<String> tempList = new ArrayList<>();
					tempList.add(message.getPersonId());
					tempList.add(message.getMessage());
					return tempList;
				})
				.collect(Collectors.toList());

		return formattedMessages;
	}

	public Channel getChannel(String channel_id) {
		return channelRepository.getChannel(channel_id)
				.orElseThrow(() -> new RuntimeException("Channel not found for ID: " + channel_id));
	}

	public void addMessageToChannel(Message message) {
		messagesService.addMessage(message);
	}

	public List<Channel> getChannels() {
		return channelRepository.getChannels();
	}
}
