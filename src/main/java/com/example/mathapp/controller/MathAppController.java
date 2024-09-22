package com.example.mathapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

/**
 * 控制 Math App 主页面的控制器类。
 */
@Controller
public class MathAppController {

    /**
     * 显示 Math App 主页面。
     *
     * @return Math App 主页面的视图名称
     */
    @GetMapping("/mathapp")
    public String showMathAppPage() {
        return "mathapp";
    }

    /**
     * 处理根路径的请求，重定向到 /mathapp。
     *
     * @return 重定向至 /mathapp
     */
    @GetMapping("/")
    public String redirectToMathApp() {
        return "redirect:/mathapp"; // 将根路径重定向到 /mathapp
    }
}
