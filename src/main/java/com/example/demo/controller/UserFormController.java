package com.example.demo.controller;

/**
 * @author lm
 * @date 2023/12/22 19:22
 */
import com.example.demo.entity.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class UserFormController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField emailField;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Event> tableView;
    @FXML
    private TableColumn<Event, String> usernameColumn;
    @FXML
    private TableColumn<Event, String> emailColumn;

    private ObservableList<Event> users = FXCollections.observableArrayList();
    private boolean isEditing = false;

    /**
     * 新增
     */
    @FXML
    private void handleAddAction() {
    }

    /**
     * 编辑
     */
    @FXML
    private void handleEditAction() {
    }

    /**
     * 删除
     */
    @FXML
    private void handleDeleteAction() {
    }

    /**
     * 校验
     * @return
     */
    private boolean validateFields() {
        // Add your validation logic here
        return true;
    }

    /**
     * 清空
     */
    private void clearFields() {
    }

    public void handleTableClick(MouseEvent mouseEvent) {
    }
}