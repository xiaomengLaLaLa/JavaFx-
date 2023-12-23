package com.example.demo.controller;

import com.example.demo.MainApplication;
import com.example.demo.RegisterApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;


/**
 * @author lm
 */
public class RegisterController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button registerButton;

    // 报错语句
    Alert confirmationAlert = new Alert(AlertType.ERROR);

    /**
     * 点击注册按钮执行的函数
     */
    public void handleRegisterAction() throws SQLException {

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
        // 先校验数据库是否有相同的用户名
        if (MainApplication.dbUtilInstance.executeQuery("select * from user where username =?", new String[]{username}).next()) {
            confirmationAlert.setTitle("用户名已存在");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("该用户名已存在，请重新输入！");
            confirmationAlert.showAndWait();
            return;
        }
        // 执行插入语句

        int result = MainApplication.dbUtilInstance.executeUpdate("insert into user(username, password) values(?,?)", new String[]{username, password});
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
    /**
     * 跳转到登录界面
     */
    public void handleLoginLinkAction() {
        try {
            FXMLLoader registerLoader = new FXMLLoader(RegisterApplication.class.getResource("login.fxml"));
            Parent registerRoot = registerLoader.load();

            Stage registerStage = new Stage();
            registerStage.setTitle("注册");

            Scene scene = new Scene(registerRoot);
            registerStage.setScene(scene);
            registerStage.show();

            ((Stage) registerButton.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}