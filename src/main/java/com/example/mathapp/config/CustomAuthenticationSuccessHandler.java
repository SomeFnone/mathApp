package com.example.mathapp.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * 自定义登录成功处理器。
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.debug("登录成功，用户：{}", authentication.getName());

        // 设置登录成功的Cookie
        Cookie loggedInCookie = new Cookie("loggedIn", "true");
        loggedInCookie.setPath("/");
        response.addCookie(loggedInCookie);

        Cookie usernameCookie = new Cookie("currentUsername", authentication.getName());
        usernameCookie.setPath("/");
        response.addCookie(usernameCookie);

        // 重定向到首页
        response.sendRedirect("/home");
    }
}