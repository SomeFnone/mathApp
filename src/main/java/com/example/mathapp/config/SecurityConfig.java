package com.example.mathapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login", "/register", "/register/sendVerificationEmail","/error", "/quiz", "/generate-quiz", "/navigate-question",
                                        "/submit-answers", "/check-errors", "/navigate-error","/changePassword","function_selection_page","home").permitAll() // 允许未认证用户访问登录、注册和错误页面
                                // .requestMatchers("/quiz").authenticated() // 允许认证用户访问选题、答题页面
                                .anyRequest().authenticated()
                )
                .logout(logout ->
                        logout
                                .permitAll()
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}