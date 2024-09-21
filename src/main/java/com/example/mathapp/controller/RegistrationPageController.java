package com.example.mathapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationPageController {

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        // 返回Thymeleaf模板名称
        return "register";
    }
}