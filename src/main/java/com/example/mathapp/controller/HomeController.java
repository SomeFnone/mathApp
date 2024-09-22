package com.example.mathapp.controller;

import com.example.mathapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 用户控制器，处理用户的登录、注册和密码修改请求。
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserService userService;

    /**
     * 显示用户主页页面。
     *
     * @return 登录页面的视图名称
     */
    @GetMapping("/home")
    public String home() {
        return "home";
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
     * 处理用户修改密码的请求。
     *
     * <p>此方法接收一个包含用户名、旧密码和新密码的JSON对象作为请求体，
     * 并尝试更改用户的密码。如果更改成功，则返回一个带有状态和消息的HTTP响应。
     *
     * @param request 包含密码更改所需信息的Map，键为"username"、"oldPassword"和"newPassword"
     * @return ResponseEntity对象，包含一个Map作为响应体，其中包含状态和消息
     */
    @PutMapping("/change-password")
    public ResponseEntity<Map<String, String>> changePassword(@RequestBody Map<String, String> request) {
        boolean success = userService.changePassword(
                request.get("username"),
                request.get("oldPassword"),
                request.get("newPassword")
        );
        if(success) return ResponseEntity.ok(Map.of("status", "success", "message", "修改成功！"));
        else return ResponseEntity.status(400).body(Map.of("status", "error", "message", "旧密码错误！"));

    }
}
