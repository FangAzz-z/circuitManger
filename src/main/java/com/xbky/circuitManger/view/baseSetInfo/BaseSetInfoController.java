package com.xbky.circuitManger.view.baseSetInfo;

import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.dao.BaseInfoDao;
import com.xbky.circuitManger.entity.ProductType;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.view.baseSetPt.BaseSetPtAddController;
import com.xbky.circuitManger.view.common.FxmlView;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class BaseSetInfoController implements Initializable {

    Logger logger = LoggerFactory.getLogger(BaseSetInfoController.class);

    BaseInfoDao baseInfoDao = new BaseInfoDao();

    //故障现象
    @FXML
    public TextField show_text_content;
    @FXML
    public TableColumn status_id;
    @FXML
    public TableColumn status_content;
    @FXML
    public TableView status_table;


    //维修产品状态
    @FXML
    public TextField status_text_content;
    @FXML
    public TableColumn show_id;
    @FXML
    public TableColumn show_content;
    @FXML
    public TableView show_table;

    //维修措施
    @FXML
    public TextField method_text_content;
    @FXML
    public TableColumn method_id;
    @FXML
    public TableColumn method_content;
    @FXML
    public TableView method_table;


    //处理结果
    @FXML
    public TextField result_text_content;
    @FXML
    public TableColumn result_id;
    @FXML
    public TableColumn result_content;
    @FXML
    public TableView result_table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.status_id.setCellValueFactory(new MapValueFactory<String>("id"));
        this.status_content.setCellValueFactory(new MapValueFactory<>("content"));

        this.show_id.setCellValueFactory(new MapValueFactory<String>("id"));
        this.show_content.setCellValueFactory(new MapValueFactory<String>("content"));

        this.method_id.setCellValueFactory(new MapValueFactory<String>("id"));
        this.method_content.setCellValueFactory(new MapValueFactory<String>("content"));

        this.result_id.setCellValueFactory(new MapValueFactory<String>("id"));
        this.result_content.setCellValueFactory(new MapValueFactory<String>("content"));
        refreshStatusData();
        refreshShowData();
        refreshMethodData();
        refreshResultData();

    }
    private void commonAdd(Runnable handle,String label) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.BASESET_INFO_DIALOG.fxml()));
            Parent root = loader.<Parent>load();
            Scene sence = new Scene(root, 600, 300); // 页面大小
            Stage dialog = new Stage();
            dialog.setScene(sence);
            dialog.setTitle(label+"-基础信息-添加");
            dialog.initStyle(StageStyle.UTILITY);
            dialog.initOwner(Main.mainStage);
            dialog.centerOnScreen();
            BaseSetInfoAddController controller = loader.getController();
            controller.setDialog(dialog);
            controller.setBaseData("",label,"");
            controller.setResultHandld(handle);
            dialog.show();
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    private void commonModify( Map<String,Object> map,Runnable handle,String label) {
        try {
            if (ObjectUtil.isNull(map)) {
                StageManager.nullWarn("提示","请选中某一行");
                return;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.BASESET_INFO_DIALOG.fxml()));
            Parent root = loader.load();
            Scene sence = new Scene(root, 600, 300); // 页面大小
            Stage dialog = new Stage();
            dialog.setScene(sence);
            dialog.setTitle(label+"-基础信息-修改");
            dialog.initStyle(StageStyle.UTILITY);
            dialog.initOwner(Main.mainStage);
            dialog.centerOnScreen();
            BaseSetInfoAddController  controller = loader.getController();
            controller.setDialog(dialog);
            controller.setBaseData(map.get("id")+"",label,(String)map.get("content"));
            controller.setResultHandld(handle);
            dialog.show();
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    //维修产品状态
    public void refreshStatusData(){
        ObservableList<Map<String,Object>> list = FXCollections.observableArrayList();
        List<Map<String,Object>> dataList = this.baseInfoDao.commonQueryAll("CM_BASE_PT_STATUS");
        list.addAll(dataList);
        this.status_table.getSelectionModel().clearSelection();
        this.status_table.setItems(list);
        this.status_table.refresh();
    }
    public void statusReset(ActionEvent actionEvent) {
        this.status_text_content.clear();
    }
    public void statusQuery(ActionEvent actionEvent) {
        if (ObjectUtil.isAllNull(this.status_text_content.getText())) {
            refreshStatusData();
            return;
        }
        ObservableList<Map<String,Object>> list = FXCollections.observableArrayList();
        List<Map<String,Object>> dataList = this.baseInfoDao.queryByExample("CM_BASE_PT_STATUS",this.status_text_content.getText());
        list.addAll(dataList);
        this.status_table.getSelectionModel().clearSelection();
        this.status_table.setItems(list);
        this.status_table.refresh();
    }

    public void statusAdd(ActionEvent actionEvent) {
        commonAdd(()->{refreshStatusData();},"维修产品状态");
    }

    public void statusModify(ActionEvent actionEvent) {
        commonModify((Map)this.status_table.getSelectionModel().getSelectedItem(),()->{refreshStatusData();},"维修产品状态");
    }

    public void statusDelete(ActionEvent actionEvent) {
        Map<String,Object> map = (Map)this.status_table.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            StageManager.nullWarn("提示","请选中某一行");
            return;
        }
        if(StageManager.deleteTrue()) {
            this.baseInfoDao.commonDeleteById("CM_BASE_PT_STATUS", (Long) map.get("id") + "");
            refreshStatusData();
        }
    }

    
    //故障现象
    public void refreshShowData(){
        List<Map<String,Object>> dataList =  this.baseInfoDao.commonQueryAll("CM_BASE_FAULT_SHOW");
        ObservableList<Map<String,Object>> list = FXCollections.observableArrayList();
        list.addAll(dataList);
        this.show_table.getSelectionModel().clearSelection();
        this.show_table.setItems(list);
        this.show_table.refresh();
    }
    public void showReset(ActionEvent actionEvent) {
        this.show_text_content.clear();
    }
    public void showQuery(ActionEvent actionEvent) {
        if (ObjectUtil.isAllNull(this.show_text_content.getText())) {
            refreshShowData();
            return;
        }
        List<Map<String,Object>> dataList =  this.baseInfoDao.queryByExample("CM_BASE_FAULT_SHOW",this.show_text_content.getText());
        ObservableList<Map<String,Object>> list = FXCollections.observableArrayList();
        list.addAll(dataList);
        this.show_table.getSelectionModel().clearSelection();
        this.show_table.setItems(list);
        this.show_table.refresh();
    }

    public void showAdd(ActionEvent actionEvent) {
        commonAdd(()->{refreshShowData();},"故障现象");
    }

    public void showModify(ActionEvent actionEvent) {
        commonModify((Map)this.show_table.getSelectionModel().getSelectedItem(),()->{refreshShowData();},"故障现象");
    }

    public void showDelete(ActionEvent actionEvent) {
        Map<String,Object> map = (Map)this.show_table.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            StageManager.nullWarn("提示","请选中某一行");
            return;
        }
        if(StageManager.deleteTrue()) {
            this.baseInfoDao.commonDeleteById("CM_BASE_FAULT_SHOW", (Long) map.get("id") + "");
            refreshShowData();
        }
    }

    //维修措施
    public void refreshMethodData(){
        ObservableList<Map<String,Object>> list = FXCollections.observableArrayList();
        List<Map<String,Object>> dataList =  this.baseInfoDao.commonQueryAll("CM_BASE_MAINTAIN_METHOD");
        list.addAll(dataList);
        this.method_table.getSelectionModel().clearSelection();
        this.method_table.setItems(list);
        this.method_table.refresh();
        
    }
    public void methodReset(ActionEvent actionEvent) {
        this.method_text_content.clear();
    }
    public void methodQuery(ActionEvent actionEvent) {
        if (ObjectUtil.isAllNull(this.method_text_content.getText())) {
            refreshMethodData();
            return;
        }
        ObservableList<Map<String,Object>> list = FXCollections.observableArrayList();
        List<Map<String,Object>> dataList =  this.baseInfoDao.queryByExample("CM_BASE_MAINTAIN_METHOD",this.method_text_content.getText());
        list.addAll(dataList);
        this.method_table.getSelectionModel().clearSelection();
        this.method_table.setItems(list);
        this.method_table.refresh();
    }

    public void methodAdd(ActionEvent actionEvent) {
        commonAdd(()->{refreshMethodData();},"维修措施");
    }

    public void methodModify(ActionEvent actionEvent) {
        commonModify((Map)this.method_table.getSelectionModel().getSelectedItem(),()->{refreshMethodData();},"维修措施");
    }

    public void methodDelete(ActionEvent actionEvent) {
        Map<String,Object> map = (Map)this.method_table.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            StageManager.nullWarn("提示","请选中某一行");
            return;
        }
        if(StageManager.deleteTrue()) {
            this.baseInfoDao.commonDeleteById("CM_BASE_MAINTAIN_METHOD", map.get("id") + "");
            refreshMethodData();
        }
    }

    //处理结果
    public void refreshResultData(){
        ObservableList<Map<String,Object>> list = FXCollections.observableArrayList();
        List<Map<String,Object>> dataList =  this.baseInfoDao.commonQueryAll("CM_BASE_HANDLE_RESULT");
        list.addAll(dataList);
        this.result_table.getSelectionModel().clearSelection();
        this.result_table.setItems(list);
        this.result_table.refresh();

    }
    public void resultReset(ActionEvent actionEvent) {
        this.result_text_content.clear();
    }

    public void resultQuery(ActionEvent actionEvent) {
        if (ObjectUtil.isAllNull(this.result_text_content.getText())) {
            refreshResultData();
            return;
        }
        ObservableList<Map<String,Object>> list = FXCollections.observableArrayList();
        List<Map<String,Object>> dataList =  this.baseInfoDao.queryByExample("CM_BASE_HANDLE_RESULT",this.result_text_content.getText());
        list.addAll(dataList);
        this.result_table.getSelectionModel().clearSelection();
        this.result_table.setItems(list);
        this.result_table.refresh();
    }

    public void resultAdd(ActionEvent actionEvent) {
        commonAdd(()->{refreshResultData();},"处理结果");
    }

    public void resultModify(ActionEvent actionEvent) {
        commonModify((Map)this.result_table.getSelectionModel().getSelectedItem(),()->{refreshResultData();},"处理结果");
    }

    public void resultDelete(ActionEvent actionEvent) {
        Map<String,Object> map = (Map)this.result_table.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            StageManager.nullWarn("提示","请选中某一行");
            return;
        }
        if(StageManager.deleteTrue()) {
            this.baseInfoDao.commonDeleteById("CM_BASE_HANDLE_RESULT", map.get("id") + "");
            refreshResultData();
        }
    }
}
