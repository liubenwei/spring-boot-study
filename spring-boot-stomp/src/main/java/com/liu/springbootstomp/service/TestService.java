package com.liu.springbootstomp.service;

import com.alibaba.fastjson.JSON;
import com.liu.springbootstomp.model.RequestMessage;
import com.liu.springbootstomp.rabbitmq.MessageHelper;
import com.liu.springbootstomp.rabbitmq.Sender;
import com.liu.springbootstomp.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author liu
 */
@Service
public class TestService {
	@Autowired
	private Sender sender;

	public void test() {
		RequestMessage mqTask = new RequestMessage();
		for (int i = 0; i < 6; i++) {
			mqTask.setRoom("123");
			mqTask.setUserId("000");
			mqTask.setType("2");
			mqTask.setQuestionId("0000");
			mqTask.setCreateTime("0000");
			mqTask.setContent("this:" + i);

			sender.send(MessageHelper.objectToMessage(mqTask));
		}
	}
}
