package com.example.demo.dao.impl;

import com.example.demo.MainApplication;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;

import java.sql.ResultSet;

/**
 * @author lm
 * @since 2023/12/24 1:32
 */
public class UserDaoImpl implements UserDao {
    /**
     * 新增
     * @param user
     */
    public int add(User user) {
        return MainApplication.dbUtilInstance.executeUpdate
                ("insert into user(username, password) values(?,?)"
                        , new String[]{user.getUsername(), user.getPassword()});
    }

    /**
     * 查询数据
     */
    public ResultSet findUserInfo(String username, String password) {
        if (password == null) {
            return MainApplication.dbUtilInstance.executeQuery("SELECT * FROM user WHERE username = ? "
                   , new String[]{username});
        } else {
            return MainApplication.dbUtilInstance.executeQuery("SELECT * FROM user WHERE username = ? AND password = ? "
                    , new String[]{username, password});
        }
    }
}
