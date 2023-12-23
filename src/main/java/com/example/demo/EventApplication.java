package com.example.demo;

import com.example.demo.utils.DBUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * @author lm
 */
public class EventApplication extends Application {
    public static DBUtil dbUtilInstance;

    @Override
    public void start(Stage primaryStage) throws Exception {
        if (dbUtilInstance == null) {
            dbUtilInstance = new DBUtil();
            dbUtilInstance.getConn();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(EventApplication.class.getResource("event.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("Event Page");

        // 获取当前屏幕的宽度和高度
        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getBounds().getWidth();
        double screenHeight = screen.getBounds().getHeight();

        // 调整舞台的宽度和高度为屏幕的80%
        primaryStage.setWidth(screenWidth * 0.8);
        primaryStage.setHeight(screenHeight * 0.8);

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
