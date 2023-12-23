package com.example.demo.controller;

import com.example.demo.dao.entity.Event;
import com.example.demo.utils.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.time.LocalDate;

/**
 * @author zrx
 * @since 2023/12/23 16:00
 */
public class EditDialogController {
    @FXML
    private DialogPane editDialogPane;

    @FXML
    private Integer id;

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

    private ObservableList<String> unitTypes = FXCollections.observableArrayList("小时", "天", "周", "月", "年");

    private DBUtil dbUtil = new DBUtil();

    // 错误提示
    Alert failureAlert = new Alert(Alert.AlertType.ERROR);

    // 成功提示
    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);

    private Event eventToEdit; // 用于存储待编辑的事件对象

    public void setDateTime(LocalDate dateTime) {
        this.datetime.setValue(dateTime);
    }

    public void setShipName(String shipName) {
        this.shipName.setText(shipName);
    }

    public void setUserName(String username) {
        this.username.setText(username);
    }

    public void setResidenceTime(String residenceTime) {
        this.residenceTime.setText(residenceTime);
    }

    public void setDesc(String desc) {
        this.desc.setText(desc);
    }

    public void setUnit(String unit) {
        this.unit.setValue(unit);
    }

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

    // 编辑事件处理方法
    @FXML
    private void handleEditButtonClick() {
        try {
            dbUtil.getConn();
            if (datetime.getValue() != null) {
                // 更新数据库中的数据
                int updateFlag = dbUtil.executeUpdate(
                        "update event set datetime = ?, shipName = ?, userName = ?, residenceTime = ?, `desc` = ?, unit = ? where id = ?"
                        , new String[]{
                                datetime.getValue().toString(),
                                shipName.getText(),
                                username.getText(),
                                residenceTime.getText(),
                                desc.getText(),
                                unit.getValue(),
                                String.valueOf(id)
                        });
                if (updateFlag == 0) {
                    failureAlert = new Alert(Alert.AlertType.ERROR);
                    failureAlert.setTitle("编辑失败");
                    failureAlert.setHeaderText(null);
                    failureAlert.setContentText("请重试！");
                    failureAlert.showAndWait();
                } else {
                    successAlert.setTitle("编辑成功");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("编辑成功");
                    successAlert.showAndWait();
                }
            } else {
                failureAlert = new Alert(Alert.AlertType.ERROR);
                failureAlert.setTitle("编辑失败");
                failureAlert.setHeaderText(null);
                failureAlert.setContentText("请输入开始时间！");
                failureAlert.showAndWait();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbUtil.closeAll();
        }
        // 关闭对话框
        editDialogPane.getScene().getWindow().hide();
    }

    @FXML
    private void handleCloseButtonClick() {
        // 关闭对话框
        editDialogPane.getScene().getWindow().hide();
    }

    public LocalDate getDateTime() {
        return datetime.getValue();
    }
    public String getShipName() {
        return shipName.getText();
    }
    public String getUserName() {
        return username.getText();
    }

    public String getResidenceTime() {
        return residenceTime.getText();
    }
    public String getDesc() {
        return desc.getText();
    }
    public String getUnit() {
        return unit.getValue();
    }

    public void setId(int id) {
        this.id = id;
    }
}