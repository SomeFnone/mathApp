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
     * @param email    用户的邮箱
     * @param password 用户的密码
     */
    public void register(String email, String password) {
        User user = new User(email, password);
        userRepository.save(user);
    }

    /**
     * 用户登录验证。
     *
     * @param email    用户的邮箱
     * @param password 用户的密码
     * @return 登录成功返回 true，否则返回 false
     */
    public boolean login(String email, String password) {
        User user = userRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    /**
     * 修改用户密码。
     *
     * @param email         用户的邮箱
     * @param oldPassword   旧密码
     * @param newPassword   新密码
     */
    public void changePassword(String email, String oldPassword, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            userRepository.save(user);
        }
    }
}
