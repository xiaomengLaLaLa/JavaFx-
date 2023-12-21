package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.io.IOException;

public class HelloApplication extends Application {
    private ImageView imageView;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("test.fxml"));
        // 根节点
        BorderPane root = fxmlLoader.load();

        // 获取左边的AnchorPane
        AnchorPane leftAnchorPane = (AnchorPane) root.getLeft();
        // 获取里面的子组件--get(0)是获取第一个
        imageView = (ImageView) leftAnchorPane.getChildren().get(0);

        // 创建一个窗口
        Scene scene = new Scene(root, 300, 300);
        // 标题
        stage.setTitle("Hello!");
        stage.setScene(scene);

        // 添加窗口大小改变事件监听器，就是监听窗口的变化
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            // 窗口发生改变的时候，触发updateImageViewSize函数
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                updateImageViewSize();
            }
        });

        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                updateImageViewSize();
            }
        });

        stage.show();
    }

    /**
     * 调用这个调整图片大小
     */
    private void updateImageViewSize() {
        // 获取到宽度高度
        double windowWidth = imageView.getScene().getWindow().getWidth();
        double windowHeight = imageView.getScene().getWindow().getHeight();

        Bounds bounds = imageView.localToScene(imageView.getBoundsInLocal());
        double imageWidth = bounds.getWidth();
        double imageHeight = bounds.getHeight();

        double aspectRatio = imageWidth / imageHeight;

        // 调整页面为之前宽度的70%
        double newWidth = windowWidth * 0.7;
        double newHeight = newWidth / aspectRatio;

        imageView.setFitWidth(newWidth);
        imageView.setFitHeight(newHeight);
    }

    public static void main(String[] args) {
        launch();
    }
}