package com.masai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class AppConfig implements WebSocketMessageBrokerConfigurer{

	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
	
		registry.addEndpoint("/masai").withSockJS();
		//withSockJs, if the websocket connection is disconnected, or
		//the websocket connection is not establihed then connection will be
		//downgrade to http and connection bt client and server still continue.
		
		
		
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
	
		registry.enableSimpleBroker("/topic");
		registry.setApplicationDestinationPrefixes("/app");
	}
	
	
	
	
}
