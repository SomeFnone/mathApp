package com.example.mathapp.controller;

import com.example.mathapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 控制用户登录页面的控制器类。
 */
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final UserService userService;

    /**
     * 构造函数，注入 UserService。
     *
     * @param userService 用户服务，用于处理与用户相关的业务逻辑
     */
    public LoginController(UserService userService) {
        this.userService = userService;
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
}
