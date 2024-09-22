package com.example.mathapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 邮件服务的实现类，用于发送验证码邮件。
 */
@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender; // 邮件发送器

    @Autowired
    private Environment env; // 环境对象，用于获取配置属性

    /**
     * 发送验证码到指定邮箱地址。
     *
     * @param to 收件人的邮箱地址
     * @param verificationCode 要发送的验证码
     */
    @Override
    public void sendVerificationCode(String to, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("验证码");
        message.setText("您的验证码是：" + verificationCode);
        message.setFrom(env.getProperty("spring.mail.username")); // 从配置中获取发件人邮箱地址

        try {
            mailSender.send(message); // 发送邮件
            logger.info("邮件发送成功至: {}", to); // 记录成功日志
        } catch (Exception e) {
            logger.error("邮件发送失败，错误信息: ", e); // 记录错误日志
        }
    }
}
