package com.xbky.circuitManger.view.system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;

import java.net.URL;
import java.util.*;

public class SystemDataController  implements Initializable {
    @FXML
    public TableColumn id;
    @FXML
    public TableColumn dataSource;
    @FXML
    public TableColumn button;
    @FXML
    public TableView userTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.id.setCellValueFactory(new MapValueFactory<String>("id"));
        this.dataSource.setCellValueFactory(new MapValueFactory<String>("dataSource"));
        this.button.setCellValueFactory(new  MapValueFactory<String>("button"));
        List<DataItem> list =new ArrayList<>();
        DataItem item1 = new DataItem("1","维修登记单");
        DataItem item2 = new DataItem("2","配件登记单");
        DataItem item3 = new DataItem("3","产品类别型号");
        DataItem item4 = new DataItem("3","维修人员");
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        List<Map<String, Object>> list2 = new ArrayList();
        list.forEach(a->{
            Map<String, Object> map = new HashMap<>();
            map.put("id",a.id);
            map.put("dataSource", a.dataSource);
            map.put("button",a.button);
            list2.add(map);
        });
        ObservableList<Map<String,Object>> olist = FXCollections.observableArrayList();
        olist.addAll(list2);
        this.userTable.getSelectionModel().clearSelection();
        this.userTable.setItems(olist);
        this.userTable.refresh();
    }

    private void refreshData() {

    }
}
