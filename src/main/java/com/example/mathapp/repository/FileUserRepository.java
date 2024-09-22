package com.example.mathapp.repository;

import com.example.mathapp.model.User;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;

/**
 * 用户数据存储类，使用文件系统来存储用户数据。
 */
@Repository
public class FileUserRepository {

    private static final String FILE_PATH = "users.txt";
    private final Map<String, User> userMap = new HashMap<>();

    public FileUserRepository() {
        loadUsersFromFile();
    }

    /**
     * 从文件加载用户数据。
     */
    private void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    userMap.put(parts[0], new User(parts[0], parts[1], parts[2])); // username,email,password
                }
            }
        } catch (IOException e) {
            // 文件不存在时不做处理
        }
    }

    /**
     * 将用户数据保存到文件。
     */
    private void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : userMap.values()) {
                writer.write(user.getUsername() + "," + user.getEmail() + "," + user.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存用户数据。
     *
     * @param user 用户对象
     */
    public void save(User user) {
        userMap.put(user.getUsername(), user);
        saveUsersToFile();
    }

    /**
     * 根据邮箱查找用户。
     *
     * @param email 用户的邮箱
     * @return 用户对象，如果未找到则返回 null
     */
    public User findByEmail(String email) {
        return userMap.values().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    /**
     * 根据用户名查找用户。
     *
     * @param username 用户的用户名
     * @return 用户对象，如果未找到则返回 null
     */
    public User findByUsername(String username) {
        return userMap.get(username);
    }

    /**
     * 获取所有用户数据。
     *
     * @return 用户列表
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }
}
