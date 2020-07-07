package com.liu.springbootstomp.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.liu.springbootstomp.model.RequestMessage;
import com.liu.springbootstomp.utils.JsonUtils;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

/**
 * @author liu
 */
@Component
public class Receiver {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @RabbitListener(queues = "test.queue")
    public void process(Message message, Channel channel) throws IOException {
        RequestMessage mqTask = new RequestMessage();
        String context = new String(message.getBody());
        mqTask = JSON.parseObject(context,RequestMessage.class);
        MessageProperties properties = message.getMessageProperties();
        if (Objects.equals( mqTask.getType(), "2" )) {
            String destination = "/topic/" +mqTask.getRoom();
            System.out.println("receiver:"+destination);
            messagingTemplate.convertAndSend( destination, mqTask);
            channel.basicAck(properties.getDeliveryTag(),false);
        }

    }





















}
