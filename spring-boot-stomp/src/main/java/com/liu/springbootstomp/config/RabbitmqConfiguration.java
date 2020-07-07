package com.liu.springbootstomp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitmqConfiguration {

	@Autowired
	private CachingConnectionFactory cachingConnectionFactory;

	public final String TEST_QUEUE_NAME = "test.queue";

	public final String TEST_EXCHANGE_NAME = "test.exchange";

	public final String TEST_ROUTING_KEY = "test.routing.key";

	@Bean
	public Queue testQueue() {
		return new Queue(TEST_QUEUE_NAME, true);
	}

	@Bean
	public DirectExchange testExchange() {
		return new DirectExchange(TEST_EXCHANGE_NAME, true, false);
	}

	@Bean
	public Binding testBinding() {
		return BindingBuilder.bind(testQueue()).to(testExchange())
				.with(TEST_ROUTING_KEY);
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(
				cachingConnectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		// 消息是否成功发送到exchange
		rabbitTemplate.setConfirmCallback(((correlationData, ack, cause) -> {
			log.info("消息发送到exchange失,{} cause :{}", correlationData, cause);
		}));
		// 触发setReturnCallback回调时，需要设置mandatory=true，否则exchange没有找到queue会丢弃消息，则不会触发回调
		rabbitTemplate.setMandatory(true);
		rabbitTemplate.setReturnCallback((message,replyCode,replyText,exchange,routingKey)->{
			log.info("消息从Exchange路由到Queue失败: exchange: {}, route: {}, replyCode: {}, replyText: {}, message: {}",
					exchange, routingKey, replyCode, replyText, message);});
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
}
