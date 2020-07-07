package com.liu.springbootstomp.controller;

import com.liu.springbootstomp.model.RequestMessage;
import com.liu.springbootstomp.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

/**
 * @author liu
 */
@RestController
public class WebSocketTestController {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Autowired
	private TestService testService;

	@CrossOrigin
	@MessageMapping("/chat")
	public void testMessageHandle(RequestMessage requestMessage) {
		String destination = "/topic/"
				+ HtmlUtils.htmlEscape(requestMessage.getRoom());

		System.out.println("controller:" + destination);
		String room = HtmlUtils.htmlEscape(requestMessage.getRoom());// htmlEscape
																		// 转换为HTML转义字符表示
		String type = HtmlUtils.htmlEscape(requestMessage.getType());
		String content = HtmlUtils.htmlEscape(requestMessage.getContent());
		String userId = HtmlUtils.htmlEscape(requestMessage.getUserId());
		String questionId = HtmlUtils
				.htmlEscape(requestMessage.getQuestionId());
		String createTime = HtmlUtils
				.htmlEscape(requestMessage.getCreateTime());

		System.out.println("room:"+requestMessage.getRoom());
		System.out.println("content"+content);

		messagingTemplate.convertAndSend(destination, requestMessage);
	}

	@GetMapping("/test/async")
	public String testAsync() {
		testService.test();
		return "http请求已结束";

	}
}
