package com.liu.springbootstomp.client;

import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {
	@Override
	public void afterConnected(StompSession session,
			StompHeaders connectedHeaders) {
		System.out.println("after Connected");
	}
}
