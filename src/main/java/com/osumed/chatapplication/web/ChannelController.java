package com.osumed.chatapplication.web;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osumed.chatapplication.services.ChannelService;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/api/channel")
public class ChannelController {
	
	@Autowired
	ChannelService channelService;

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
}

