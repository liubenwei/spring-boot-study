package com.liu.springbootstomp.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author liu
 */
@Component
public class Sender {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void send(Message context) {
		CorrelationData correlationData = new CorrelationData(String.valueOf(new Random(200000).nextInt(20000)+100000 ));
		rabbitTemplate.convertAndSend("test.exchange", "test.routing.key",context,correlationData);
	}

}
