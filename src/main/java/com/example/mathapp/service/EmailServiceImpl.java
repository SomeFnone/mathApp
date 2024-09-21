package com.example.mathapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    @Override
    public void sendVerificationCode(String to, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("验证码");
        message.setText("您的验证码是：" + verificationCode);
        message.setFrom(env.getProperty("spring.mail.username"));

        try {
            mailSender.send(message);
            logger.info("邮件发送成功至: {}", to);
        } catch (Exception e) {
            logger.error("邮件发送失败，错误信息: ", e);
        }
    }
}