package com.xbky.circuitManger.view.check;

import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.dao.CheckFittingRecordDao;
import com.xbky.circuitManger.entity.CheckFittingRecord;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.view.common.FxmlView;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class CheckFittingController implements Initializable {
    @FXML
    public Pagination pageSet;
    @FXML
    public TableView userTable;
    @FXML
    public TableColumn id;
    @FXML
    public TableColumn model;
    @FXML
    public TableColumn name;
    @FXML
    public TableColumn no;
    @FXML
    public TableColumn num;
    @FXML
    public TableColumn lowLimit;
    @FXML
    public TextField tfModel;
    @FXML
    public TextField tfName;
    @FXML
    public TextField tfNo;
    @FXML
    public TableColumn packaging;

    CheckFittingRecordDao dao = new CheckFittingRecordDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.id.setCellValueFactory(new MapValueFactory<>("id") );
        this.model.setCellValueFactory(new MapValueFactory<>("fitting_model"));
        this.name.setCellValueFactory(new MapValueFactory<>("fitting_name"));
        this.no.setCellValueFactory(new MapValueFactory<>("fitting_no"));
        this.num.setCellValueFactory(new MapValueFactory<>("fitting_num"));
        this.packaging.setCellValueFactory(new MapValueFactory<>("packaging"));
        this.lowLimit.setCellValueFactory(new MapValueFactory<>("low_limit"));
        this.pageSet.setPageFactory(pageIndex -> createPage(pageIndex));
        refreshData();

    }

    private void refreshData() {
        ObservableList<Map<String,Object>> list = FXCollections.observableArrayList();
        Map<String,Object> map =  dao.queryByExample(getSearchParam(),0,10);
        List<Map<String,Object>> dataList = (List<Map<String,Object>>)map.get("data");
        list.addAll(dataList);
        this.pageSet.setPageCount(ObjectUtil.getInt(map.get("total"))<1?1:ObjectUtil.getInt(map.get("total")));
        this.userTable.getSelectionModel().clearSelection();
        this.userTable.setItems(list);
        this.userTable.refresh();
        this.pageSet.setCurrentPageIndex(0);
        this.userTable.setRowFactory(tv->{
            TableRow<Map<String,Object>> row = new TableRow<Map<String,Object>>(){
                @Override
                protected void updateItem(Map<String, Object> item, boolean empty) {
                    super.updateItem(item, empty);
                    if(!empty) {
                        if (ObjectUtil.getInt(item.get("fitting_num")) < ObjectUtil.getInt(item.get("low_limit"))) {
                            this.setStyle("-fx-font-weight:bold;-fx-background-color: #d2ab80");
                        }
                    }
                }
            };

            return row;
        });
    }

    private CheckFittingRecord getSearchParam() {
        CheckFittingRecord record = new CheckFittingRecord();
        record.setFittingNo(this.tfNo.getText());
        record.setFittingName(this.tfName.getText());
        record.setFittingModel(this.tfModel.getText());
        return record;
    }

    private TableView<Map<String,Object>> createPage(int pageIndex){
        Map<String, Object> result = dao.queryByExample(getSearchParam(),pageIndex,10);
        List<Map<String,Object>> dataList = (List<Map<String, Object>>)result.get("data");
        ObservableList<Map<String,Object>> items = FXCollections.observableArrayList(dataList);
        userTable.setItems(items);
        return userTable;
    }

    public void reset(ActionEvent actionEvent) {
        this.tfNo.clear();
        this.tfModel.clear();
        this.tfName.clear();
    }

    public void query(ActionEvent actionEvent) {
        refreshData();
    }

    public void addData(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.CHECK_FITTING_DIALOG.fxml()));
        Parent root = loader.load();
        Scene scene = new Scene(root, 750, 600); // 页面大小
        // StageManager.initStyle(dialog);
        Stage dialog = new Stage();
        dialog.setTitle("配件库存单-添加"); // 页面标题
        dialog.setScene(scene);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initOwner(Main.mainStage);
        dialog.centerOnScreen();
        CheckFittingAddController controller = loader.getController();
        controller.setDialog(dialog);
        controller.setResultHandle(()->{refreshData();});
        dialog.show();
    }

    public void modifyData(ActionEvent actionEvent) throws IOException {
        Map<String,Object> map = (Map)userTable.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            StageManager.nullWarn("请选中某一行");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.CHECK_FITTING_DIALOG.fxml()));
        Parent root = loader.load();
        Scene scene = new Scene(root, 750, 600); // 页面大小
        // StageManager.initStyle(dialog);
        Stage dialog = new Stage();
        dialog.setTitle("配件库存单-修改"); // 页面标题
        dialog.setScene(scene);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initOwner(Main.mainStage);
        dialog.centerOnScreen();
        CheckFittingAddController controller = loader.getController();
        controller.setDialog(dialog);
        controller.setResultHandle(()->{refreshData();});
        controller.setBaseData(map);
        dialog.show();
    }

    public void deleteData(ActionEvent actionEvent) {
        Map<String,Object> map = (Map)userTable.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            StageManager.nullWarn();
            return;
        }
        if(StageManager.deleteTrue()) {
            dao.commonDeleteById("CM_CHECK_FITTING_RECORD", map.get("id") + "");
            refreshData();
        }
    }
}
