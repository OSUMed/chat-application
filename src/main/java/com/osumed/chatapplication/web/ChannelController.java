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

import com.osumed.chatapplication.domain.Channel;
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
	public String getHome(ModelMap model) {
		List<Channel> channels = channelService.getChannels();
		model.put("channels", channels);
		return "welcome";
	}

	@GetMapping("/{channel_id}")
	public String getGeneralMessages(ModelMap model, @PathVariable("channel_id") String channelId) {
		List<ArrayList<String>> messages = channelService.getMessagesFromChannel(channelId);
		Channel channel = channelService.getChannel(channelId);
		model.put("messages", messages);
		model.put("channel", channel);
		return "channel";
	}

	@PostMapping("/{channel_id}")
	@ResponseBody
	public Map<String, Object> postMessage(@RequestBody Message message, @PathVariable("channel_id") String channelId) {
		channelService.addMessageToChannel(message);
		String parsedChannelId = String.valueOf(message.getChannelId());

		// Get Items to return:
		List<ArrayList<String>> allMessages = channelService.getMessagesFromChannel(parsedChannelId);
		Integer lastMessageId = messagesService.getLastMessageId();

		// Create response and send it:
		Map<String, Object> response = new HashMap<>();
		response.put("allMessages", allMessages);
		response.put("lastMessageId", lastMessageId);
		return response;

	}
	// @PostMapping("/{channel_id}")
	// @ResponseBody
	// public Map<String, Object> postMessage(@RequestBody Message message,
	// @PathVariable("channel_id") String channelId) {
	// channelService.addMessageToChannel(message);
	// List<Message> allMessages = messagesService.getMessages();
	// Integer lastMessageId = messagesService.getLastMessageId();
	// Map<String, Object> response = new HashMap<>();
	// response.put("allMessages", allMessages);
	// response.put("lastMessageId", lastMessageId);
	// return response;

	// }

	// @GetMapping("/{channel_id}/messages")
	// @ResponseBody
	// public Map<String, Object> getMessages(@PathVariable("channel_id") String
	// channelId) {
	// List<Message> messages = channelService.getMessagesFromChannel(channelId);
	// Integer lastMessageId = messagesService.getLastMessageId();
	// Map<String, Object> response = new HashMap<>();
	// response.put("allMessages", messages);
	// response.put("lastMessageId", lastMessageId);
	// return response;
	// }
	// @GetMapping("/{channel_id}/messages")
	// @ResponseBody
	// public Map<String, Object> getMessages(@PathVariable("channel_id") String
	// channelId) {
	// List<Message> allMessages = messagesService.getMessages();
	// Integer lastMessageId = messagesService.getLastMessageId();
	// Map<String, Object> response = new HashMap<>();
	// response.put("allMessages", allMessages);
	// response.put("lastMessageId", lastMessageId);
	// return response;
	// }

}
