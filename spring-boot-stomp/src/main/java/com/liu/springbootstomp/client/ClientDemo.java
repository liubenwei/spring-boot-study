package com.liu.springbootstomp.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

public class ClientDemo {

	@Autowired
	private TaskScheduler messageBrokerTaskScheduler;

	public static void main(String[] args) {
		String url = "ws:127.0.0.1:9090/endpoint";
		StompSessionHandler sessionHandler = new MyStompSessionHandler();

		ClientDemo demo = new ClientDemo();
		WebSocketStompClient client = demo.connect();
		client.connect(url, sessionHandler);

	}

	public WebSocketStompClient connect() {
		WebSocketClient webSocketClient = new StandardWebSocketClient();
		WebSocketStompClient stompClient = new WebSocketStompClient(
				webSocketClient);
		stompClient.setMessageConverter(new StringMessageConverter());
		stompClient.setTaskScheduler(messageBrokerTaskScheduler);
		return stompClient;
	}
}
