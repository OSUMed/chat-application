package com.osumed.chatapplication.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.osumed.chatapplication.domain.Channel;
import com.osumed.chatapplication.domain.Message;
import com.osumed.chatapplication.domain.MessageDTO;
import com.osumed.chatapplication.services.ChannelService;
import com.osumed.chatapplication.services.MessagesService;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/api/channel")
public class ChannelController {

	@Autowired
	ChannelService channelService;

	@Autowired
	MessagesService messagesService;

	@GetMapping("")
	public String getHome(ModelMap model) {
		List<Channel> channels = channelService.getChannels();
		model.put("channels", channels);
		return "welcome";
	}

	@GetMapping("/{channel_id}")
	public String getGeneralMessages(ModelMap model, @PathVariable("channel_id") String channelId) {
		// List<ArrayList<String>> messages =
		channelService.getMessagesFromChannel(channelId);
		Channel channel = channelService.getChannel(channelId);
		// model.put("messages", messages);
		model.put("channel", channel);
		return "channel";
	}

	@GetMapping("/{channel_id}/messages")
	@ResponseBody
	public Map<String, Object> getChannelMessages(ModelMap model, @PathVariable("channel_id") String channelId) {
		// List<ArrayList<String>> messages =
		List<ArrayList<Object>> allMessages = channelService.getMessagesFromChannel(channelId);
		System.out.println("The coming messages in messages is: " + allMessages);
		// model.put("messages", messages);
		Integer lastMessageId = messagesService.getLastMessageId();
		Map<String, Object> response = new HashMap<>();
		response.put("allMessages", allMessages);
		response.put("lastMessageId", lastMessageId);
		return response;
	}

	// @GetMapping("/{channel_id}/messages")
	// @ResponseBody
	// public Map<String, Object> getMessages() {
	// List<Message> allMessages = messagesService.getMessages();
	// Integer lastMessageId = messagesService.getLastMessageId();
	// Map<String, Object> response = new HashMap<>();
	// response.put("allMessages", allMessages);
	// response.put("lastMessageId", lastMessageId);
	// return response;
	// }

	@PostMapping("/{channel_id}")
	@ResponseBody
	public List<ArrayList<Object>> postMessage(@RequestBody MessageDTO messageDTO,
			@PathVariable("channel_id") String channelId) {
		Message message = messagesService.convertDTOToMessage(messageDTO);

		System.out.println("What are the initial messagesDTO? " + message);
		messagesService.addMessage((message));

		// channelService.addMessageToChannel(message);
		// String parsedChannelId = String.valueOf(message.getChannelId());

		// Get Items to return:
		List<ArrayList<Object>> allMessages = channelService.getMessagesFromChannel(messageDTO.getChannelId());
		System.out.println("What are the returned messages? " + allMessages);
		System.out.println("What are the returned messages? " + allMessages);
		// Integer lastMessageId = messagesService.getLastMessageId();

		// // Create response and send it:
		// Map<String, Object> response = new HashMap<>();
		// response.put("allMessages", allMessages);
		// response.put("lastMessageId", lastMessageId);
		return allMessages;

		// @GetMapping("/general")
		// public String getGeneralMessages(ModelMap model) {
		// List<ArrayList<String>> messages = channelService.getMessages("general");
		// model.put("messages", messages);
		// return "general";
	}

	// @GetMapping("/test")
	// @ResponseBody
	// public String getTest() {
	// return "This is a test";
	// }

	// @PostMapping("/general")
	// @ResponseBody
	// public Map<String, Object> postMessage(@RequestBody Message message) {
	// System.out.println("received message is: " + message);

	// // 1. Save the received message to the database
	// channelService.addMessage(message);
	// List<Message> allMessages = messagesService.getMessages();
	// Integer lastMessageId = messagesService.getLastMessageId();
	// Map<String, Object> response = new HashMap<>();
	// response.put("allMessages", allMessages);
	// response.put("lastMessageId", lastMessageId);
	// return response;
	// // 2. Fetch the updated list of messages

	// // 3. Return the updated messages as a response

	// }

}
