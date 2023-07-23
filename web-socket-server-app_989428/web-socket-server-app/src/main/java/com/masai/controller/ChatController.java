package com.masai.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.masai.model.Message;

@Controller
public class ChatController {

	
	@SendTo("/topic/public")
	@MessageMapping("/chat.register")
	public Message register(@Payload Message message, SimpMessageHeaderAccessor headerAccesor) {
		
		headerAccesor.getSessionAttributes().put("username", message.getSender());
		
		return message;
		
		
	}
	
	@SendTo("/topic/public")
	@MessageMapping("/chat.send")
	public Message sendMessage(@Payload Message message) {
		return message;
	}
	
	
	
	
	
}
