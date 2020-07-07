package com.liu.springbootstomp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration
		implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/websocket").setAllowedOrigins("*").withSockJS()
				.setSessionCookieNeeded(true);
		registry.addEndpoint("/websocket-rabbitmq").setAllowedOrigins("*")
				.withSockJS();
	}



	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app");
		registry.enableSimpleBroker("/topic", "/all");

		// registry.enableStompBrokerRelay("/exchange", "/topic", "/queue")
		// .setRelayHost("192.168.124.19").setClientLogin("test1")
		// .setClientPasscode("test1").setSystemLogin("test2")
		// .setSystemPasscode("test2");
		//
		// registry.enableStompBrokerRelay("/topic", "/queue")
		// .setRelayHost("127.0.0.1").setRelayPort(61613)
		// .setClientLogin("guest").setClientPasscode("guest");
	}

}
