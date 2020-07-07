package com.liu.springbootstomp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * @author liu
 */
@Controller
public class GreetingController {
	@MessageMapping("/greeting")
	public String handle(String greeting) {
		return "[" + System.currentTimeMillis() + ":" + greeting;
	}
}
