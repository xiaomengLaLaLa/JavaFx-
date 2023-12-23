package com.example.demo.controller;

import com.example.demo.RegisterApplication;
import com.example.demo.utils.DBUtil;
import javafx.event.ActionEvent;
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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    // 数据库连接
    private DBUtil dbUtil = new DBUtil();
    // 错误提示
    Alert failureAlert = new Alert(AlertType.ERROR);

    /**
     * 点击Login按钮执行的函数
     * @param event
     */
    public void handleLoginAction(ActionEvent event) throws SQLException {

        String username = usernameField.getText();
        String password = passwordField.getText();

        // 登录验证逻辑

        // 获取数据库连接
        dbUtil.getConn();
        // 根据用户名密码查询用户
        ResultSet resultSet = dbUtil.executeQuery("SELECT * FROM user WHERE username = ? AND password = ? "
                , new String[]{username, password});
        // 没查询到数据
        if (!resultSet.next()) {
            System.out.println("登陆失败");
            failureAlert = new Alert(AlertType.ERROR);
            failureAlert.setTitle("登录失败");
            failureAlert.setHeaderText(null);
            failureAlert.setContentText("用户名或密码错误，请重试！");
            failureAlert.showAndWait();
        } else {
            // 查询出来了数据，存在该用户
            System.out.println("登陆成功");
            Alert successAlert = new Alert(AlertType.INFORMATION);
            successAlert.setTitle("登录成功");
            successAlert.setHeaderText(null);
            successAlert.setContentText("欢迎，" + username + "！");
            successAlert.showAndWait();
        }
        dbUtil.closeAll();
    }

    /**
     * 跳转到注册界面
     * @param event
     */
    public void handleRegisterLinkAction(ActionEvent event) {
        try {
            FXMLLoader registerLoader = new FXMLLoader(RegisterApplication.class.getResource("register.fxml"));
            Parent registerRoot = registerLoader.load();

            Stage registerStage = new Stage();
            registerStage.setTitle("注册");

            Scene scene = new Scene(registerRoot);
            registerStage.setScene(scene);
            registerStage.show();

            ((Stage) loginButton.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}