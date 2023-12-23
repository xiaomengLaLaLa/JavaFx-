package com.example.demo.controller;

import com.example.demo.utils.DBUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class RegisterController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button registerButton;

    private DBUtil dbUtil = new DBUtil();

    // 报错语句
    Alert confirmationAlert = new Alert(AlertType.ERROR);

    /**
     * 点击注册按钮执行的函数
     * @param event
     */
    public void handleRegisterAction(ActionEvent event) throws SQLException {

        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // 验证确认密码是否与密码一致
        if (!password.equals(confirmPassword)) {
            confirmationAlert.setTitle("确认密码错误");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("两次输入的密码不一致，请重新输入！");
            confirmationAlert.showAndWait();
            return;
        }

        // 注册逻辑
        dbUtil.getConn();
        // 先校验数据库是否有相同的用户名
        if (dbUtil.executeQuery("select * from user where username =?", new String[]{username}).next()) {
            confirmationAlert.setTitle("用户名已存在");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("该用户名已存在，请重新输入！");
            confirmationAlert.showAndWait();
            return;
        }
        // 执行插入语句

        int result = dbUtil.executeUpdate("insert into user(username, password) values(?,?)", new String[]{username, password});
        if (result == 0) {
            confirmationAlert.setTitle("注册失败");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("注册失败，请重试！");
            confirmationAlert.showAndWait();
        }
        System.out.println("注册成功，用户名：" + username);

        // 显示成功提示框
        Alert successAlert = new Alert(AlertType.INFORMATION);
        successAlert.setTitle("注册成功");
        successAlert.setContentText("注册成功！");
        successAlert.setHeaderText(null);
        successAlert.showAndWait();
    }
}