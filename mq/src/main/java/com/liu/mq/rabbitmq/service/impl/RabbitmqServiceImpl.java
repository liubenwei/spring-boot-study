package com.liu.mq.rabbitmq.service.impl;

import com.alibaba.fastjson.JSON;
import com.liu.mq.rabbitmq.config.RabbitConfig;
import com.liu.mq.rabbitmq.consumer.MessageHelper;
import com.liu.mq.rabbitmq.dao.MsgLogDao;
import com.liu.mq.rabbitmq.entity.MsgLog;
import com.liu.mq.rabbitmq.model.Mail;
import com.liu.mq.rabbitmq.service.RabbitmqService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RabbitmqServiceImpl implements RabbitmqService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MsgLogDao msgLogDao;

    @Override
    public void send(Mail mail){
        MsgLog msgLog =new MsgLog();
        msgLog.setMsgId(UUID.randomUUID().toString());
        msgLog.setMsg(JSON.toJSONString(mail));
        msgLog.setExchange(RabbitConfig.MAIL_EXCHANGE_NAME);
        msgLog.setRoutingKey(RabbitConfig.MAIL_ROUTING_KEY_NAME);
        msgLog.setStatus(0);
        msgLog.setTryCount(0);
        msgLogDao.insert(msgLog);
        mail.setMsgId(msgLog.getMsgId());
        CorrelationData correlationData = new CorrelationData(msgLog.getMsgId());
        rabbitTemplate.convertAndSend(RabbitConfig.MAIL_EXCHANGE_NAME,RabbitConfig.MAIL_ROUTING_KEY_NAME,
                MessageHelper.objectToMessage(mail),correlationData);
    }
}
