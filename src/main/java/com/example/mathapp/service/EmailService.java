package com.example.mathapp.service;


public interface EmailService {
    void sendVerificationCode(String to, String verificationCode);
}