package com.xbky.circuitManger.view.module;

import com.xbky.circuitManger.entity.ProductType;
import com.xbky.circuitManger.service.ProdectTypeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class BaseSetPtController implements Initializable {
    @FXML
    private TableView userTable;

    @FXML
    private TableColumn id;
    @FXML
    private TableColumn category;
    @FXML
    private TableColumn model;
    @FXML
    private TableColumn brand;

    ProdectTypeService prodectTypeService = new  ProdectTypeService();



    public void deleteUsers(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.id.setCellValueFactory(new MapValueFactory<String>("id"));
        this.category.setCellValueFactory(new MapValueFactory<String>("category"));
        this.model.setCellValueFactory(new MapValueFactory<String>("model"));
        this.brand.setCellValueFactory(new MapValueFactory<String>("brand"));
        ObservableList<Map<String,Object>> list = FXCollections.observableArrayList();
        List<Map<String,Object>> dataList =  prodectTypeService.queryAll();
        list.addAll(dataList);
        this.userTable.setItems(list);
    }
}
