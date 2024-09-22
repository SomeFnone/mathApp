package com.example.mathapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mathAppPageController {

    @GetMapping("/mathApp")
    public String showMathAppPage() {
        return "mathApp";
    }
}
