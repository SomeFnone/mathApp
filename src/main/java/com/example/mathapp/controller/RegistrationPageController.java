package com.example.mathapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 负责显示用户注册页面的控制器类。
 */
@Controller
public class RegistrationPageController {

    /**
     * 显示用户注册页面。
     *
     * @param model 用于在视图中传递数据
     * @return 注册页面的视图名称
     */
    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        return "register";
    }
}
