package com.example.mathapp.controller;

import com.example.mathapp.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 处理用户登录请求。
     *
     * @param request  HttpServletRequest 对象
     * @param response HttpServletResponse 对象
     * @param username 用户的邮箱
     * @param password 用户的密码
     * @return 登录成功后重定向到功能选择页面，否则返回登录页面
     */
    @PostMapping("/login")
    public ModelAndView loginUser(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @RequestParam String username,
                                  @RequestParam String password) {
        logger.debug("Attempting to login with username: {}", username); // 日志输出

        if (userService.login(username, password)) {
            // 设置 Cookie 表示用户已登录
            Cookie loggedInCookie = new Cookie("loggedIn", "true");
            loggedInCookie.setPath("/");
            response.addCookie(loggedInCookie);

            Cookie usernameCookie = new Cookie("currentUsername", username);
            usernameCookie.setPath("/");
            response.addCookie(usernameCookie);

            return new ModelAndView("/home"); // 成功后重定向到功能选择页面
        } else {
            // 添加错误参数以便前端显示错误信息
            return new ModelAndView("redirect:/login?error=true");
        }
    }
}
