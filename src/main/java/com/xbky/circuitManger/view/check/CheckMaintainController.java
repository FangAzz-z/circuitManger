package com.xbky.circuitManger.view.check;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckMaintainController implements Initializable {
    @FXML
    public ComboBox queryModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String week_days[] =
                { "Monday", "Tuesday", "Wednesday",
                        "Thrusday", "Friday" };
        queryModel.setItems(FXCollections
                .observableArrayList(week_days));
    }

    public void addData(ActionEvent actionEvent) {
    }

    public void modifyData(ActionEvent actionEvent) {
    }

    public void deleteData(ActionEvent actionEvent) {
    }

    public void reset(ActionEvent actionEvent) {
    }

    public void query(ActionEvent actionEvent) {
    }
}
