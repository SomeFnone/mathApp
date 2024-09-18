package com.example.mathapp.model;

/**
 * 用户实体类。
 */
public class User {

    private String email;
    private String password;

    // 构造函数
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // 获取邮箱
    public String getEmail() {
        return email;
    }

    // 设置邮箱
    public void setEmail(String email) {
        this.email = email;
    }

    // 获取密码
    public String getPassword() {
        return password;
    }

    // 设置密码
    public void setPassword(String password) {
        this.password = password;
    }
}
