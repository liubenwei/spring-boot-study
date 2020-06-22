package com.liu.mq.rabbitmq.util;

import com.liu.mq.rabbitmq.model.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailUtil {
    @Value("${spring.mail.from}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;

    public boolean send(Mail mail){
        String to = mail.getTo();
        String title = mail.getTitle();
        String content = mail.getContent();

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
        message.setText(content);
        try{
            mailSender.send(message);
            return true;
        }catch (MailException e){
            log.error("邮件发送失败, to:{}, title:{}",to, title,e);
            return false;
        }

    }
}
