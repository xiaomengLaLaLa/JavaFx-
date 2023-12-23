package com.example.demo.controller;

import com.example.demo.dao.EventDao;
import com.example.demo.entity.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

/**
 * @author zrx
 * @since 2023/12/23 16:00
 */
public class AddDialogController {
    @FXML
    private DialogPane addDialogPane;

    @FXML
    private DatePicker datetime;

    @FXML
    private TextField shipName;

    @FXML
    private TextField username;

    @FXML
    private TextField residenceTime;

    @FXML
    private TextField desc;

    @FXML
    private ComboBox<String> unit;

    private final EventDao eventDao = new EventDao();

    private final ObservableList<String> unitTypes = FXCollections.observableArrayList("小时", "天", "周", "月", "年");

    // 错误提示
    Alert failureAlert = new Alert(Alert.AlertType.ERROR);

    // 成功提示
    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);

    public void initialize() {
        // 添加驻留时间输入框的键入事件监听器
        residenceTime.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!"0123456789".contains(event.getCharacter())) {
                event.consume(); // 消耗非数字字符事件
            }
        });
        unit.setItems(unitTypes);
        unit.setValue("小时"); // 设置默认选项为"小时"
    }

    // 新增的事件处理方法
    @FXML
    private void handleAddButtonClick() {
        // 获取表单所有数据
        try {
            if (datetime.getValue() != null) {
                // 向数据库中插入数据
                Event event = new Event(0, datetime.getValue(), shipName.getText(), username.getText(), residenceTime.getText() != null ? Integer.parseInt(residenceTime.getText()) : 0, desc.getText(), unit.getValue());
                int updateFlag = eventDao.add(event);
                if (updateFlag == 0) {
                    failureAlert = new Alert(Alert.AlertType.ERROR);
                    failureAlert.setTitle("添加失败");
                    failureAlert.setHeaderText(null);
                    failureAlert.setContentText("请重试！");
                    failureAlert.showAndWait();
                } else {
                    successAlert.setTitle("添加成功");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("添加成功");
                    successAlert.showAndWait();
                }
                System.out.println(event);

            } else {
                failureAlert = new Alert(Alert.AlertType.ERROR);
                failureAlert.setTitle("添加失败");
                failureAlert.setHeaderText(null);
                failureAlert.setContentText("请输入开始时间！");
                failureAlert.showAndWait();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 关闭对话框
        addDialogPane.getScene().getWindow().hide();
    }

    @FXML
    private void handleCloseButtonClick() {
        // 关闭对话框
        addDialogPane.getScene().getWindow().hide();
    }
}
