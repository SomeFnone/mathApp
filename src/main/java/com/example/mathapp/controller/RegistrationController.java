package com.example.mathapp.controller;

import com.example.mathapp.service.EmailService;
import com.example.mathapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 处理用户注册请求的控制器类。
 */
@RestController
@RequestMapping("/register")
public class RegistrationController {

    private final Map<String, String> verificationCodes = new HashMap<>(); // 存储验证码

    @Autowired
    private EmailService emailService; // 注入邮件服务
    @Autowired
    private UserService userService;

    /**
     * 发送验证码至用户邮箱的接口。
     *
     * @param request 包含用户邮箱信息的请求体
     * @return 成功或失败的状态
     */
    @PostMapping("/sendVerificationEmail")
    public ResponseEntity<Map<String, String>> sendVerificationEmail(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String verificationCode = generateVerificationCode();

        try {
            verificationCodes.put(email, verificationCode); // 保存验证码
            emailService.sendVerificationCode(email, verificationCode); // 发送邮件
            return ResponseEntity.ok(Map.of("status", "success"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("status", "error", "message", "验证码发送失败"));
        }
    }

    /**
     * 用户注册接口，验证验证码并完成注册。
     *
     * @param request 包含用户名、邮箱、密码和验证码的请求体
     * @return 注册成功或失败的状态
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> register(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String username = request.get("username");
        String verificationCode = request.get("verificationCode");

        if (verificationCode.equals(verificationCodes.get(email))) {
            userService.register(username, email, request.get("password"));
            verificationCodes.remove(email); // 注册成功后删除验证码
            return ResponseEntity.ok(Map.of("status", "success", "message", "注册成功！"));
        } else if (!verificationCode.equals(verificationCodes.get(email))) {
            return ResponseEntity.status(400).body(Map.of("status", "error", "message", "验证码错误"));
        }
        return ResponseEntity.status(400).body(Map.of("status", "error", "message", "未知错误"));
    }

    /**
     * 生成一个6位数的随机验证码。
     *
     * @return 随机生成的6位验证码
     */
    private String generateVerificationCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}