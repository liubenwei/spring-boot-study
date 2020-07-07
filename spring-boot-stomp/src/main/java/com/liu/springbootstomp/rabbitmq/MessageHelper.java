package com.liu.springbootstomp.rabbitmq;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Component;

/**
 * @author liu
 */
@Component
public class MessageHelper {

    public static Message objectToMessage(Object object) {
        if (object == null) {
            return null;
        }
        Message message = MessageBuilder.withBody(JSON.toJSONString(object).getBytes()).build();
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        message.getMessageProperties().setContentType(MessageProperties.CONTENT_TYPE_JSON);

        return message;
    }

    public static <T> T messageToObject(Message message, Class<T> clazz) {

        if (message == null && null == clazz) {
            return null;
        }
        String string = new String(message.getBody());
        T object = JSON.parseObject(string, clazz);
        return object;

    }
}