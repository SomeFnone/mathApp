package com.example.mathapp.repository;

import com.example.mathapp.model.User;
import org.springframework.stereotype.Repository;

/**
 * 用户数据访问层接口。
 */
@Repository
public interface UserRepository {

    /**
     * 根据邮箱查找用户。
     *
     * @param email 用户的邮箱
     * @return 用户实体
     */
    User findByEmail(String email);

    /**
     * 保存用户。
     *
     * @param user 用户实体
     */
    void save(User user);
}
