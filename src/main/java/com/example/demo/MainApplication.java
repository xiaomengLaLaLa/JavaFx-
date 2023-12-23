package com.example.demo;

import com.example.demo.utils.DBUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * @author lm
 */
public class MainApplication extends Application {

    public static DBUtil dbUtilInstance;
    @Override
    public void start(Stage primaryStage) throws Exception {
        if (dbUtilInstance == null) {
            dbUtilInstance = new DBUtil();
            dbUtilInstance.getConn();
        }
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Login Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        if (dbUtilInstance != null) {
            dbUtilInstance.closeAll();
            System.out.println("数据库连接关闭了");
        }
    }
}
