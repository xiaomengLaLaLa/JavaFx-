package com.example.demo.controller;


import com.example.demo.entity.Event;
import com.example.demo.utils.DBUtil;
import com.example.demo.utils.PropertyUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author lm
 * @date 2023/12/22 19:22
 */
public class EventController {
    @FXML
    private Stage dialogStage;

    @FXML
    private TableView<Event> tableView;

    private DBUtil dbUtil = new DBUtil();

    @FXML
    private TableColumn<Event, String> id;
    @FXML
    private TableColumn<Event, String> dateTime;
    @FXML
    private TableColumn<Event, String> shipName;
    @FXML
    private TableColumn<Event, String> userName;
    @FXML
    private TableColumn<Event, String> residenceTime;
    @FXML
    private TableColumn<Event, String> desc;
    @FXML
    private TableColumn<Event, String> unit;

    public void initialize() {
        try {

            // 设置TableView的列对应的数据源
            id.setCellValueFactory(cellData -> PropertyUtils.createProperty(String.valueOf(cellData.getValue()
                    .getId())));
            dateTime.setCellValueFactory(cellData -> PropertyUtils.createProperty(cellData.getValue().getDateTime().toString()));
            shipName.setCellValueFactory(cellData -> PropertyUtils.createProperty(cellData.getValue().getShipName()));
            userName.setCellValueFactory(cellData -> PropertyUtils.createProperty(cellData.getValue().getUserName()));
            residenceTime.setCellValueFactory(cellData -> PropertyUtils.createProperty(String.valueOf(cellData.getValue()
                    .getResidenceTime())));
            desc.setCellValueFactory(cellData -> PropertyUtils.createProperty(cellData.getValue().getDesc()));
            unit.setCellValueFactory(cellData -> PropertyUtils.createProperty(cellData.getValue().getUnit()));

            // 执行数据库查询并更新TableView数据
            executeSelectAllQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbUtil.closeAll();
        }
    }

    @FXML
    private void executeSelectAllQuery() {
        try {
            String query = "SELECT * FROM event";
            dbUtil.getConn();
            ResultSet rs = dbUtil.executeQuery(query, new String[]{});
            List<Event> events = new ArrayList<>();
            while (rs.next()) {
                Event event = new Event();
                event.setId(Integer.parseInt(rs.getString("id")));
                event.setDateTime(LocalDate.parse(rs.getString("dateTime").substring(0, 10)));
                event.setShipName(rs.getString("shipName"));
                event.setUserName(rs.getString("userName"));
                event.setResidenceTime(Integer.parseInt(rs.getString("residenceTime")));
                event.setDesc(rs.getString("desc"));
                event.setUnit(rs.getString("unit"));

                events.add(event);
            }
            tableView.getItems().clear();
            tableView.getItems().addAll(events);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.closeAll();
        }
    }

    @FXML
    private void handleAddAction() {
        try {
            dialogStage = (Stage) tableView.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/example/demo/add_dialog.fxml")));
            DialogPane dialogPane = loader.load();

            // 获取对话框中的控制器
            AddDialogController addDialogController = loader.getController();

            // 创建一个新的对话框
            Alert alert = new Alert(Alert.AlertType.NONE, "", ButtonType.OK);
            alert.initOwner(dialogStage);
            alert.setTitle("添加用户");
            alert.setDialogPane(dialogPane);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // 新增用户逻辑
//                String username = addDialogController.addUsernameField.getText();
//                String password = addDialogController.addPasswordField.getText();
//                String email = addDialogController.addEmailField.getText();

//                Event newUser = new Event(username, password, email);
//                users.add(newUser);
//                tableView.getItems().add(newUser);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleDeleteAction(ActionEvent actionEvent) {
        Event selectedEvent = tableView.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(tableView.getScene().getWindow());
            alert.setTitle("确认删除");
            alert.setHeaderText("确定要删除选定的数据吗?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    String deleteQuery = "DELETE FROM event WHERE id = ?";
                    dbUtil.getConn();
                    dbUtil.executeUpdate(deleteQuery, new String[]{String.valueOf(selectedEvent.getId())});
                    tableView.getItems().remove(selectedEvent);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.initOwner(tableView.getScene().getWindow());
                    alert1.setTitle("删除成功");
                    alert1.setHeaderText("选定的数据已成功删除");
                    alert1.showAndWait();
                } finally {
                    dbUtil.closeAll();
                }
            }
        } else {
            Alert noSelectionAlert = new Alert(Alert.AlertType.WARNING);
            noSelectionAlert.initOwner(tableView.getScene().getWindow());
            noSelectionAlert.setTitle("未选择事件");
            noSelectionAlert.setHeaderText("请先选择一个事件再进行删除操作");
            noSelectionAlert.showAndWait();
        }
    }
}