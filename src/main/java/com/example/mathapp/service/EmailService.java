package com.example.mathapp.service;

/**
 * 邮件服务接口，用于发送验证码邮件。
 */
public interface EmailService {

    /**
     * 发送验证码到指定邮箱。
     *
     * @param to 收件人的邮箱地址
     * @param verificationCode 要发送的验证码
     */
    void sendVerificationCode(String to, String verificationCode);
}
