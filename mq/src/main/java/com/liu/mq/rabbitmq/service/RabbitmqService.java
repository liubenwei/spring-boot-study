package com.liu.mq.rabbitmq.service;

import com.liu.mq.rabbitmq.model.Mail;

public interface RabbitmqService {
    public void send(Mail mail);
}
