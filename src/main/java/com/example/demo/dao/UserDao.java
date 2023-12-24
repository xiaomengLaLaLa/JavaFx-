package com.example.demo.dao;

import com.example.demo.MainApplication;
import com.example.demo.entity.User;

import java.sql.ResultSet;

/**
 * @author lm
 * @since 2023/12/24 1:32
 */
public interface UserDao {
    /**
     * 新增
     * @param user
     */
    public int add(User user);

    /**
     * 查询数据
     */
    public ResultSet findUserInfo(String username, String password);
}
