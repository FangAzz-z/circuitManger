package com.xbky.circuitManger.view.produce;

import com.xbky.circuitManger.dao.ProgramDao;
import com.xbky.circuitManger.service.ProgramService;
import com.xbky.circuitManger.utils.DialogUtil;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;

import java.io.File;
import java.net.URL;
import java.util.*;

public class ProduceProController implements Initializable {
    @FXML
    public TableColumn id;
    @FXML
    public TableColumn produceName;
    @FXML
    public TableColumn produceUrl;
    @FXML
    public TableColumn urlSet;
    @FXML
    public TableColumn button;
    @FXML
    public TableView userTable;

    ProgramService service = new ProgramService();
    ProgramDao dao = new ProgramDao();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.id.setCellValueFactory(new MapValueFactory<String>("id"));
        this.produceName.setCellValueFactory(new MapValueFactory<String>("produceName"));
        this.produceUrl.setCellValueFactory(new MapValueFactory<String>("produceUrl"));
        this.urlSet.setCellValueFactory(new MapValueFactory<String>("urlSet"));
        this.button.setCellValueFactory(new  MapValueFactory<String>("button"));
        List<Map<String,Object>> dataList = dao.commonQueryAll("CM_PROGRAM_LOCATION");
        List<ProduceItem> list = new ArrayList<ProduceItem>();
        for (int i = 0; i < dataList.size(); i++) {
            Map<String, Object> t = dataList.get(i);
            ProduceItem item =  new ProduceItem(ObjectUtil.getString(t.get("id")), ObjectUtil.getString(t.get("program_name")),ObjectUtil.getString(t.get("program_location")));
            list.add(item);
        }
        List<Map<String, Object>> list2 = new ArrayList();
        for (int i = 0; i < list.size(); i++) {

            final ProduceItem a = list.get(i);
            final Map<String, Object> map = new HashMap<String, Object>();
            map.put("id",a.id);
            map.put("produceName", a.produceName);
            map.put("produceUrl", a.produceUrl);
            map.put("urlSet", a.urlSet);
            map.put("button",a.button);
            a.urlSet.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    File exePathFile = DialogUtil.showExportExeFileDialog("请选择程序路径");
                    if (exePathFile != null) {
                        map.put("produceUrl", exePathFile.getPath());
                        ProduceProController.this.userTable.refresh();
                        dao.modifyByName(a.produceName, exePathFile.getPath());
                    }
                }
            });
            a.button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (ObjectUtil.isNull(a.produceUrl)) {

                    } else {
                        try {
                            service.programRun(a.produceName);
                        } catch (Exception e) {
                            StageManager.nullWarn(e.getMessage());
                        }
                    }
                }
            });
            list2.add(map);
        }

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
