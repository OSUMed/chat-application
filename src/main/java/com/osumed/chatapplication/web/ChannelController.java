package com.osumed.chatapplication.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.osumed.chatapplication.domain.Message;

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
	public String getHome() {
		return "welcome";
	}

	@GetMapping("/{channel_id}")
	public String getGeneralMessages(ModelMap model, @PathVariable("channel_id") String channelId)) {
		List<ArrayList<String>> messages = channelService.getMessages(channelId);
		model.put("messages", messages);
		return "general";
	}

	@GetMapping("/test")
	@ResponseBody
	public String getTest() {
		return "This is a test";
	}

	@PostMapping("/general")
	@ResponseBody
	public Map<String, Object> postMessage(@RequestBody Message message) {
		System.out.println("received message is: " + message);

		// 1. Save the received message to the database
		channelService.addMessage(message);
		List<Message> allMessages = messagesService.getMessages();
		Integer lastMessageId = messagesService.getLastMessageId();
		Map<String, Object> response = new HashMap<>();
		response.put("allMessages", allMessages);
		response.put("lastMessageId", lastMessageId);
		return response;
		// 2. Fetch the updated list of messages

		// 3. Return the updated messages as a response

	}

	@GetMapping("/general/messages")
	@ResponseBody
	public Map<String, Object> getMessages() {
		List<Message> allMessages = messagesService.getMessages();
		Integer lastMessageId = messagesService.getLastMessageId();
		Map<String, Object> response = new HashMap<>();
		response.put("allMessages", allMessages);
		response.put("lastMessageId", lastMessageId);
		return response;
	}

}
