package com.example.demo.controller;

import com.example.demo.EventApplication;
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
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lm
 */
public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    // 错误提示
    Alert failureAlert = new Alert(AlertType.ERROR);

    /**
     * 点击Login按钮执行的函数
     */
    public void handleLoginAction() throws SQLException, IOException {

        String username = usernameField.getText();
        String password = passwordField.getText();

        // 登录验证逻辑

        // 获取数据库连接
        // 根据用户名密码查询用户
        ResultSet resultSet = MainApplication.dbUtilInstance.executeQuery("SELECT * FROM user WHERE username = ? AND password = ? "
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

            // 登陆成功，跳转到事件界面
            Stage eventStage = new Stage();
            eventStage.setTitle("欢迎进入【中国航工航天里程碑信息管理系统】");

            FXMLLoader eventLoader = new FXMLLoader(EventApplication.class.getResource("event.fxml"));
            Parent eventRoot = eventLoader.load();
            Scene eventScene = new Scene(eventRoot);
            // 获取当前屏幕的宽度和高度
            Screen screen = Screen.getPrimary();
            double screenWidth = screen.getBounds().getWidth();
            double screenHeight = screen.getBounds().getHeight();

            // 调整舞台的宽度和高度为屏幕的80%
            eventStage.setWidth(screenWidth * 0.8);
            eventStage.setHeight(screenHeight * 0.8);

            eventStage.setScene(eventScene);
            eventStage.show();
            // 关闭登录窗口
            ((Stage) loginButton.getScene().getWindow()).close();
        }
    }

    /**
     * 跳转到注册界面
     */
    public void handleRegisterLinkAction() {
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