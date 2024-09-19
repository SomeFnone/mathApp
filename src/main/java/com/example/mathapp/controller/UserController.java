package com.example.mathapp.controller;

import com.example.mathapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户控制器，处理用户注册、登录和密码修改请求。
 */
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    /**
     * 显示用户注册页面。
     *
     * @return 注册页面的视图名称
     */
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * 处理用户注册请求。
     *
     * @param email    用户的邮箱
     * @param password 用户的密码
     * @return 重定向到登录页面
     */
    @PostMapping("/register")
    public String registerUser(@RequestParam String email, @RequestParam String password) {
        userService.register(email, password);
        return "redirect:/login";
    }

    /**
     * 显示用户登录页面。
     *
     * @return 登录页面的视图名称
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 处理用户登录请求。
     *
     * @param username    用户的邮箱
     * @param password 用户的密码
     * @return 登录成功后重定向到试卷生成页面，否则返回登录页面
     */
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        logger.debug("Attempting to login with email: {}", username); // 日志输出
        if (userService.login(username, password)) {
            return "redirect:/quiz";
        }
        // 重定向
        return "redirect:/login";
    }

    /**
     * 显示密码修改页面。
     *
     * @return 密码修改页面的视图名称
     */
    @GetMapping("/change-password")
    public String changePassword() {
        return "change-password";
    }

    /**
     * 处理密码修改请求。
     *
     * @param email         用户的邮箱
     * @param oldPassword   旧密码
     * @param newPassword   新密码
     * @return 密码修改成功后重定向到登录页面
     */
    @PostMapping("/change-password")
    public String changePassword(@RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword) {
        userService.changePassword(email, oldPassword, newPassword);
        return "redirect:/login";
    }
}
