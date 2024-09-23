package com.example.mathapp.config;

import com.example.mathapp.service.FileBasedUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 安全配置类，配置Spring Security。
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final FileBasedUserDetailsService fileBasedUserDetailsService;
    private final CustomAuthenticationSuccessHandler successHandler;
    private final CustomAuthenticationFailureHandler failureHandler;

    public SecurityConfig(FileBasedUserDetailsService fileBasedUserDetailsService,
                          CustomAuthenticationSuccessHandler successHandler,
                          CustomAuthenticationFailureHandler failureHandler) {
        this.fileBasedUserDetailsService = fileBasedUserDetailsService;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }
    /**
     * 配置安全过滤链，设置不同页面的访问权限。
     *
     * @param http HttpSecurity 对象
     * @return SecurityFilterChain
     * @throws Exception 如果配置出错抛出异常
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers( "/", "/mathapp", "/login",
                                "/register", "/register/sendVerificationEmail", "/error")
                        .permitAll() // 允许未认证用户访问空url、首页、登录、注册和错误页面
                        .anyRequest().authenticated() // 允许认证用户访问选题、答题等其他页面
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("username") //用户名字段
                        .passwordParameter("password") //密码字段
                        .successHandler(successHandler) // 自定义登录成功处理
                        .failureHandler(failureHandler) // 自定义登录失败处理
                        .permitAll() // 放开登录页面的访问权
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // 配置注销的URL
                        .logoutSuccessUrl("/mathapp") //注销成功后的跳转页面
                        .deleteCookies() //清除cookies
                        .permitAll() // 配置注销
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.sendRedirect("/mathapp"); // 未认证用户访问受限页面时重定向到 /mathapp
                        })
                )
                .csrf(csrf -> csrf.disable()); // 禁用CSRF

        return http.build();
    }
}
