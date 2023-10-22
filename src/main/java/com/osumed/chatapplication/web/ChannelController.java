package com.osumed.chatapplication.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/channel")
public class ChannelController {

	@GetMapping("/")
	public String getHome() {
		return "welcome";
	}
    @GetMapping("/general")
    public String getGeneralMessages() {
        return "general";
    }
}

