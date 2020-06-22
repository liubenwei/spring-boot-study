package com.liu.mq.rabbitmq.consumer;

import com.liu.mq.rabbitmq.config.RabbitConfig;
import com.liu.mq.rabbitmq.constant.MailConstant;
import com.liu.mq.rabbitmq.entity.MsgLog;
import com.liu.mq.rabbitmq.model.Mail;
import com.liu.mq.rabbitmq.service.MsgLogService;
import com.liu.mq.rabbitmq.util.MailUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @author liu
 * 2020/6/21
 */
@Component
@Slf4j
public class MailConsumer {
    @Autowired
    private MsgLogService msgLogService;

    @Autowired
    private MailUtil mailUtil;


    @RabbitListener(queues = RabbitConfig.MAIL_QUEUE_NAME)
    public void consume(Message message, Channel channel) throws IOException {
        Mail mail = MessageHelper.messageToObject(message, Mail.class);
        log.info("收到消息:{}", mail.toString());
        String messageId = mail.getMsgId();
        MsgLog msgLog = msgLogService.queryById(messageId);
        if (null == msgLog || msgLog.getStatus().equals(MailConstant.msgLogStatus.CONSUMED_SUCCESS)) {

            log.info("重复消费:{}", messageId);
            return;
        }
        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();
        boolean success = mailUtil.send(mail);
        if (success) {
            msgLog.setStatus(MailConstant.msgLogStatus.CONSUMED_SUCCESS);
            msgLogService.update(msgLog);
            channel.basicAck(tag, false);
        } else {
            channel.basicNack(tag, false, true);
        }


    }


}
