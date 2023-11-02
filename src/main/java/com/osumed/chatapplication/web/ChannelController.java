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
	public String getChannelDetails(ModelMap model, @PathVariable("channel_id") String channelId) {
		Channel channel = channelService.getChannel(channelId);
		model.put("channel", channel);
		return "channel";
	}

	@GetMapping("/{channel_id}/messages")
	@ResponseBody
	public Map<String, Object> getChannelMessages(ModelMap model, @PathVariable("channel_id") String channelId) {
		Map<String, Object> response = new HashMap<>();
		List<ArrayList<Object>> allMessages = channelService.getMessagesFromChannel(channelId);
		Integer lastMessageId = messagesService.getLastMessageId();
		response.put("allMessages", allMessages);
		response.put("lastMessageId", lastMessageId);
		return response;
	}

	@PostMapping("/{channel_id}/messages")
	@ResponseBody
	public List<ArrayList<Object>> postMessage(@RequestBody MessageDTO messageDTO,
			@PathVariable("channel_id") String channelId) {

		// messageDTO is used to create message entity. message entity is added and all
		// messages are queried to be returned:
		Message message = messagesService.convertDTOToMessage(messageDTO);
		messagesService.addMessage((message));

		// Get Items to return:
		List<ArrayList<Object>> allMessages = channelService.getMessagesFromChannel(messageDTO.getChannelId());
		return allMessages;
	}

}
