package com.example.demo;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button registerButton;

    /**
     * 点击注册按钮执行的函数
     * @param event
     */
    public void handleRegisterAction(ActionEvent event) {

        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // 验证确认密码是否与密码一致
        if (!password.equals(confirmPassword)) {
            Alert confirmationAlert = new Alert(AlertType.ERROR);
            confirmationAlert.setTitle("确认密码错误");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("两次输入的密码不一致，请重新输入！");
            confirmationAlert.showAndWait();
            return;
        }

        // 注册逻辑（这里只是一个示例，实际应用中可能需要连接数据库等操作）
        System.out.println("注册成功，用户名：" + username);

        // 显示成功提示框
        Alert successAlert = new Alert(AlertType.INFORMATION);
        successAlert.setTitle("注册成功");
        successAlert.setHeaderText(null);
        successAlert.setContentText("注册成功，欢迎，" + username + "！");
        successAlert.showAndWait();
    }
}