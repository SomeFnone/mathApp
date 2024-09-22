package com.example.mathapp.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class mathAppPageController {

    @GetMapping("/mathApp")
    public String showMathAppPage() {
        return "mathApp";
    }
}
