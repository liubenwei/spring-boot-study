package com.liu.mq.rabbitmq.task;

import com.liu.mq.rabbitmq.constant.MailConstant;
import com.liu.mq.rabbitmq.consumer.MessageHelper;
import com.liu.mq.rabbitmq.entity.MsgLog;
import com.liu.mq.rabbitmq.service.MsgLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MailResendMessage {
    @Autowired
    private MsgLogService msgLogService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //最大投递次数
    private static final int MAX_RETRY_COUNT = 3;

    @Scheduled(cron = "0/30 * * * * ?")
    public void resend(){
        log.info("开始执行定时任务:重新投递邮件到rabbitmq");
        List<MsgLog> msgLogList = msgLogService.selectTimeoutMsg();

        msgLogList.forEach(msgLog -> {
            String msgId = msgLog.getMsgId();
            if(msgLog.getTryCount() >= MAX_RETRY_COUNT){
                msgLog.setStatus(MailConstant.msgLogStatus.DELIVER_FAIL);
                msgLogService.update(msgLog);
            }else{
                msgLog.setTryCount(msgLog.getTryCount() + 1);
                msgLogService.update(msgLog);
                CorrelationData correlationData = new CorrelationData(msgId);
                rabbitTemplate.convertAndSend(msgLog.getExchange(),msgLog.getRoutingKey(), MessageHelper.objectToMessage(msgLog.getMsg()), correlationData);
            }
        });

        log.info("定时任务介绍");

    }


}
