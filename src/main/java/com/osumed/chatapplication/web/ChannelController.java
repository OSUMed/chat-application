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

	@GetMapping("/")
	public String getHome() {
		return "welcome";
	}

	@GetMapping("/general")
	public String getGeneralMessages(ModelMap model) {
		List<ArrayList<String>> messages = channelService.getMessages("general");
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
	public List<Message> postMessage(@RequestBody Message message) {
		System.out.println("received message is: " + message);

		// 1. Save the received message to the database
		messagesService.addMessage(message);
		List<Message> allMessages = messagesService.getMessages();
		// 2. Fetch the updated list of messages

		// 3. Return the updated messages as a response

		return allMessages;
	}

}
