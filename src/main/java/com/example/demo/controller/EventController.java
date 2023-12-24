package com.example.demo.controller;


import com.example.demo.dao.EventDao;
import com.example.demo.dao.impl.EventDaoImpl;
import com.example.demo.entity.Event;
import com.example.demo.utils.PropertyUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author lm
 * @since 2023/12/22 19:22
 */
public class EventController {
    @FXML
    private Stage dialogStage;

    @FXML
    private TableView<Event> tableView;

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

    private final EventDao eventDao = new EventDaoImpl();

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
        }
    }

    @FXML
    private void executeSelectAllQuery() throws Exception {
        try {
            List<Event> events = eventDao.findAll();
            tableView.getItems().clear();
            tableView.getItems().addAll(events);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddAction() {
        try {
            dialogStage = (Stage) tableView.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/example/demo/add_dialog.fxml")));
            DialogPane dialogPane = loader.load();

            // 获取对话框中的控制器
            loader.getController();

            // 创建一个新的对话框
            Alert alert = new Alert(Alert.AlertType.NONE, "", ButtonType.OK);
            alert.initOwner(dialogStage);
            alert.setTitle("添加用户");
            alert.setDialogPane(dialogPane);

            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleDeleteAction() {
        Event selectedEvent = tableView.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(tableView.getScene().getWindow());
            alert.setTitle("确认删除");
            alert.setHeaderText("确定要删除选定的数据吗?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    eventDao.delete(String.valueOf(selectedEvent.getId()));
                    tableView.getItems().remove(selectedEvent);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.initOwner(tableView.getScene().getWindow());
                    alert1.setTitle("删除成功");
                    alert1.setHeaderText("选定的数据已成功删除");
                    alert1.showAndWait();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            Alert noSelectionAlert = new Alert(Alert.AlertType.WARNING);
            noSelectionAlert.initOwner(tableView.getScene().getWindow());
            noSelectionAlert.setTitle("未选择数据");
            noSelectionAlert.setHeaderText("请先选择一个数据再进行删除操作");
            noSelectionAlert.showAndWait();
        }
    }

    @FXML
    public void handleEditAction(ActionEvent actionEvent) throws Exception {
        Event selectedEvent = tableView.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            dialogStage = (Stage) tableView.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/example/demo/edit_dialog.fxml")));
            DialogPane dialogPane = loader.load();

            // 获取对话框中的控制器
            EditDialogController editDialogController = getEditDialogController(loader, selectedEvent);

            // 创建一个新的对话框
            Alert alert = new Alert(Alert.AlertType.NONE, "", ButtonType.OK, ButtonType.CANCEL);
            alert.initOwner(dialogStage);
            alert.setTitle("编辑数据");
            alert.setDialogPane(dialogPane);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // 更新用户逻辑
                LocalDate dateTime = editDialogController.getDateTime();
                String shipName = editDialogController.getShipName();
                String userName = editDialogController.getUserName();
                int residenceTime = Integer.parseInt(editDialogController.getResidenceTime());
                String desc = editDialogController.getDesc();
                String unit = editDialogController.getUnit();

                selectedEvent.setDateTime(dateTime);
                selectedEvent.setShipName(shipName);
                selectedEvent.setUserName(userName);
                selectedEvent.setResidenceTime(residenceTime);
                selectedEvent.setDesc(desc);
                selectedEvent.setUnit(unit);

                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.initOwner(tableView.getScene().getWindow());
                alert1.setTitle("编辑成功");
                alert1.setHeaderText("选定的数据已成功更新");
                alert1.showAndWait();
            }
        } else {
            Alert noSelectionAlert = new Alert(Alert.AlertType.WARNING);
            noSelectionAlert.initOwner(tableView.getScene().getWindow());
            noSelectionAlert.setTitle("未选择数据");
            noSelectionAlert.setHeaderText("请先选择一个数据再进行编辑操作");
            noSelectionAlert.showAndWait();
        }
        // 编辑之后，重新触发查询
        executeSelectAllQuery();
    }

    private static EditDialogController getEditDialogController(FXMLLoader loader, Event selectedEvent) {
        EditDialogController editDialogController = loader.getController();

        // 设置对话框中的字段值为选定数据的属性
        editDialogController.setId(selectedEvent.getId());
        editDialogController.setDateTime(selectedEvent.getDateTime());
        editDialogController.setShipName(selectedEvent.getShipName());
        editDialogController.setUserName(selectedEvent.getUserName());
        editDialogController.setResidenceTime(String.valueOf(selectedEvent.getResidenceTime()));
        editDialogController.setDesc(selectedEvent.getDesc());
        editDialogController.setUnit(selectedEvent.getUnit());
        return editDialogController;
    }
}