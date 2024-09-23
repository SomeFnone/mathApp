package com.example.mathapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 控制器类，用于处理与数学应用程序相关的页面请求
@Controller
public class mathAppPageController {

    // 响应GET请求，用于显示数学应用程序的页面
    @GetMapping("/mathApp")
    public String showMathAppPage() {
        return "mathApp";
    }
}
