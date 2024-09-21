package com.example.mathapp.service;

import com.example.mathapp.model.User;
import com.example.mathapp.repository.FileUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务类，处理用户的注册、登录和密码管理。
 */
@Service
public class UserService {

    @Autowired
    private FileUserRepository userRepository;

    /**
     * 注册一个新用户。
     *
     * @param username 用户名
     * @param email    用户的邮箱
     * @param password 用户的密码
     * @throws IllegalArgumentException 如果用户名已存在
     */
    public void register(String username, String email, String password) {
        if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("用户名已存在");
        } else if (userRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException("邮箱已注册");
        }
        User user = new User(username, email, password);
        userRepository.save(user);
    }

    /**
     * 用户登录验证。
     *
     * @param username 用户名
     * @param password 用户的密码
     * @return 登录成功返回 true，否则返回 false
     */
    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    /**
     * 修改用户密码。
     *
     * @param username     用户名
     * @param oldPassword  旧密码
     * @param newPassword  新密码
     */
    public void changePassword(String username, String oldPassword, String newPassword) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            userRepository.save(user);
        }
    }
}
