package com.osumed.chatapplication.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osumed.chatapplication.domain.Channel;
import com.osumed.chatapplication.domain.Message;
import com.osumed.chatapplication.domain.User;
import com.osumed.chatapplication.repository.ChannelRepository;

@Service
public class ChannelService {

	private final MessagesService messagesService;
	private final UserService userService;
	private final ChannelRepository channelRepository;

	private Integer messageNumber = 0;

	@Autowired
	public ChannelService(MessagesService messagesService, UserService personService,
			ChannelRepository channelRepository) {
		this.messagesService = messagesService;
		this.userService = personService;
		this.channelRepository = channelRepository;
	}

	public List<ArrayList<String>> getMessagesFromChannel(String channelId) {
		Channel currentChannel = getChannel(channelId);
		List<Message> messages = messagesService.getMessages();

		List<ArrayList<String>> formattedMessages = messages.stream()
				.filter(message -> currentChannel.getChannelId().equals(message.getChannelId()))

				.map(message -> {
					ArrayList<String> tempList = new ArrayList<>();
					Integer userId = message.getUserId();
					Optional<User> user = userService.getUser(userId);
					System.out.println("the user is: " + user.get().getName());
					if (user.isPresent()) {
						tempList.add(user.get().getName());
						tempList.add(message.getMessage());
						System.out.println("The current add : " + tempList);
					}
					return tempList;
				})
				.collect(Collectors.toList());

		return formattedMessages;
	}

	public void addMessage(Message message) {
		System.out.println("The new message is: " + message);
		messageNumber += 1;
		message.setMessageId(messageNumber);
		messagesService.addMessage(message);
		System.out.println("The new message is: " + message);
	}

	public Channel getChannel(String channelId) {
		Integer channelIdInt = Integer.parseInt(channelId);
		return channelRepository.getChannel(channelIdInt)
				.orElseThrow(() -> new RuntimeException("Channel not found for ID: " +
						channelIdInt));
	}

	public List<Channel> getChannels() {
		return channelRepository.getChannels();
	}
}
