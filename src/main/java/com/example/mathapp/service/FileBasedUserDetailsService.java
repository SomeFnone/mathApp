package com.example.mathapp.service;

import com.example.mathapp.repository.FileUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 * 自定义 UserDetailsService 实现类，用于从文件系统加载用户数据并提供给Spring Security。
 */
@Service
public class FileBasedUserDetailsService implements UserDetailsService {

    private final FileUserRepository fileUserRepository;

    public FileBasedUserDetailsService(FileUserRepository fileUserRepository) {
        this.fileUserRepository = fileUserRepository;
    }

    /**
     * 根据用户名查找用户，并将其转换为 UserDetails 对象。
     *
     * @param username 用户的用户名
     * @return UserDetails 对象
     * @throws UsernameNotFoundException 当用户未找到时抛出异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.mathapp.model.User user = fileUserRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户未找到: " + username);
        }

        // 创建 UserDetails 对象（此处不加密密码）
        return User.withUsername(user.getUsername())
                .password("{noop}" + user.getPassword()) // 此处不加密
                .roles("USER") // 简单设置角色为 USER，需根据具体需求调整
                .build();
    }
}
