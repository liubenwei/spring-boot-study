package com.liu.mq.rabbitmq.controller;

import com.liu.mq.rabbitmq.model.Mail;
import com.liu.mq.rabbitmq.service.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class MailController {

    @Autowired
    private RabbitmqService rabbitmqService;
    @PostMapping("/mail")
    public String sendMail(@RequestParam("to") String to) {


        //todo 验证重名
        Mail mail = new Mail();
        mail.setTo(to.toString());
        mail.setTitle("注册验证码");
        Integer code = new Random().nextInt(899999) + 10000;
        mail.setVerifyCode(code);
        mail.setContent("您的注册验证码为:" + code);
        rabbitmqService.send(mail);

        return "发送chengg";
    }
}
