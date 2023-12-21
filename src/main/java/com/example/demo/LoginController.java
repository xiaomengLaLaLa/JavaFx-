package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Objects;

public class LoginController {
    public PasswordField confirmPasswordField;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    /**
     * 点击Login按钮执行的函数
     * @param event
     */
    public void handleLoginAction(ActionEvent event) {

        String username = usernameField.getText();
        String password = passwordField.getText();

        // 登录验证逻辑
        if (username.equals("小梦") && password.equals("123456")) {
            System.out.println("登陆成功");
            Alert successAlert = new Alert(AlertType.INFORMATION);
            successAlert.setTitle("登录成功");
            successAlert.setHeaderText(null);
            successAlert.setContentText("欢迎，" + username + "！");
            successAlert.showAndWait();

            // 跳转到主页面或其他操作
        } else {
            System.out.println("登陆失败");
            Alert failureAlert = new Alert(AlertType.ERROR);
            failureAlert.setTitle("登录失败");
            failureAlert.setHeaderText(null);
            failureAlert.setContentText("用户名或密码错误，请重试！");
            failureAlert.showAndWait();
        }
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
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("base.css")).toExternalForm());
            registerStage.setScene(scene);
            registerStage.show();

            ((Stage) loginButton.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}