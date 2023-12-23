package com.example.demo;

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


    @Override
    public void start(Stage primaryStage) throws Exception {
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
}
