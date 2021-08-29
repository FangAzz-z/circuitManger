package com.xbky.circuitManger.view.produce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;

import java.net.URL;
import java.util.*;

public class ProduceProController implements Initializable {
    @FXML
    public TableColumn id;
    @FXML
    public TableColumn produceName;
    @FXML
    public TableColumn produceType;
    @FXML
    public TableColumn produceUrl;
    @FXML
    public TableColumn button;
    @FXML
    public TableView userTable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.id.setCellValueFactory(new MapValueFactory<String>("id"));
        this.produceName.setCellValueFactory(new MapValueFactory<String>("produceName"));
        this.produceType.setCellValueFactory(new  MapValueFactory<String>("produceType"));
        this.produceUrl.setCellValueFactory(new MapValueFactory<String>("produceUrl"));
        this.button.setCellValueFactory(new  MapValueFactory<String>("button"));
        List<ProduceItem> list = new ArrayList<>();
        ProduceItem item1 = new ProduceItem("1","exe","program执行程序测试1","D:/workspace/tools/test.exe");
        ProduceItem item2 = new ProduceItem("2","bat","program执行程序测试2","D:/workspace/tools/test.bat");
        list.add(item1);
        list.add(item2);
        List<Map<String, Object>> list2 = new ArrayList();
        list.forEach(a->{
            Map<String, Object> map = new HashMap<>();
            map.put("id",a.id);
            map.put("produceType", a.produceType);
            map.put("produceName", a.produceName);
            map.put("produceUrl", a.produceUrl);
            map.put("button",a.button);
            list2.add(map);
        });
        ObservableList<Map<String,Object>> olist = FXCollections.observableArrayList();
        olist.addAll(list2);
        this.userTable.getSelectionModel().clearSelection();
        this.userTable.setItems(olist);
        this.userTable.refresh();
    }

    public void deleteData(ActionEvent actionEvent) {
    }

    public void modifyData(ActionEvent actionEvent) {
    }

    public void addData(ActionEvent actionEvent) {
    }
}
