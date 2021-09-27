package com.xbky.circuitManger.view.baseSetInfo;

import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.dao.BaseInfoDao;
import com.xbky.circuitManger.export.BaseFaultShowExportObj;
import com.xbky.circuitManger.importexcel.BaseFaultShowImportObj;
import com.xbky.circuitManger.utils.ExcelUtil;
import com.xbky.circuitManger.utils.ObjectUtil;
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
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class BaseSetInfoController implements Initializable {

    Logger logger = LoggerFactory.getLogger(BaseSetInfoController.class);


    //维修产品状态
    @FXML
    public TextField show_text_content;
    @FXML
    public TextField show_text_code;

    @FXML
    public TableColumn status_id;
    @FXML
    public TableColumn status_content;
    @FXML
    public TableView status_table;


    //故障现象
    @FXML
    public TableColumn show_id;
    @FXML
    public TableColumn show_content;
    @FXML
    public TableView show_table;
    @FXML
    public TableColumn show_code;

    //维修措施
    @FXML
    public TableColumn method_id;
    @FXML
    public TableColumn method_content;
    @FXML
    public TableView method_table;


    //处理结果
    @FXML
    public TableColumn result_id;
    @FXML
    public TableColumn result_content;
    @FXML
    public TableView result_table;

    @FXML
    public Pagination pageSet_result;
    @FXML
    public Pagination pageSet_method;
    @FXML
    public Pagination pageSet_show;
    @FXML
    public Pagination pageSet_status;

    BaseInfoDao dao = new BaseInfoDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.status_id.setCellValueFactory(new MapValueFactory<String>("id"));
        this.status_content.setCellValueFactory(new MapValueFactory<>("content"));

        this.show_id.setCellValueFactory(new MapValueFactory<String>("id"));
        this.show_content.setCellValueFactory(new MapValueFactory<String>("content"));
        this.show_code.setCellValueFactory(new MapValueFactory<String>("code"));

        this.method_id.setCellValueFactory(new MapValueFactory<String>("id"));
        this.method_content.setCellValueFactory(new MapValueFactory<String>("content"));

        this.result_id.setCellValueFactory(new MapValueFactory<String>("id"));
        this.result_content.setCellValueFactory(new MapValueFactory<String>("content"));
        this.pageSet_result.setPageFactory(pageIndex -> createPage(pageIndex,"CM_BASE_HANDLE_RESULT","",result_table));
        this.pageSet_method.setPageFactory(pageIndex -> createPage(pageIndex,"CM_BASE_MAINTAIN_METHOD","",method_table));
        this.pageSet_show.setPageFactory(pageIndex -> createPageV2(pageIndex,"CM_BASE_FAULT_SHOW",show_text_content.getText(),show_text_code.getText(),show_table));
        this.pageSet_status.setPageFactory(pageIndex -> createPage(pageIndex,"CM_BASE_PT_STATUS","",status_table));
        refreshStatusData();
        refreshShowData();
        refreshMethodData();
        refreshResultData();

    }

    public TableView<Map<String,Object>> createPage(int pageIndex,String tablename,String content,TableView tableView) {
        Map<String, Object> result = dao.queryByExample(tablename,content,pageIndex,8);
        List<Map<String,Object>> dataList = (List<Map<String, Object>>)result.get("data");
        ObservableList<Map<String,Object>> items = FXCollections.observableArrayList(dataList);
        tableView.getSelectionModel().clearSelection();
        tableView.setItems(items);
        tableView.refresh();
        return tableView;
    }

    public TableView<Map<String,Object>> createPageV2(int pageIndex,String tablename,String content,String code,TableView tableView) {
        Map<String, Object> result = dao.queryByExampleV2(tablename,content,code,pageIndex,8);
        List<Map<String,Object>> dataList = (List<Map<String, Object>>)result.get("data");
        ObservableList<Map<String,Object>> items = FXCollections.observableArrayList(dataList);
        tableView.getSelectionModel().clearSelection();
        tableView.setItems(items);
        tableView.refresh();
        return tableView;
    }

    private void commonAddV2(Runnable handle,String label) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.BASESET_INFO_DIALOG_V2.fxml()));
            Parent root = loader.<Parent>load();
            Scene sence = new Scene(root, 600, 300); // 页面大小
            Stage dialog = new Stage();
            dialog.setScene(sence);
            dialog.setTitle(label + "-基础信息-添加");
            dialog.initStyle(StageStyle.UTILITY);
            dialog.initOwner(Main.mainStage);
            dialog.centerOnScreen();
            BaseSetInfoAddController controller = loader.getController();
            controller.setDialog(dialog);
            controller.setResultHandld(handle);
            dialog.show();
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    private void commonAdd(Runnable handle, String label) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.BASESET_INFO_DIALOG.fxml()));
            Parent root = loader.<Parent>load();
            Scene sence = new Scene(root, 600, 300); // 页面大小
            Stage dialog = new Stage();
            dialog.setScene(sence);
            dialog.setTitle(label + "-基础信息-添加");
            dialog.initStyle(StageStyle.UTILITY);
            dialog.initOwner(Main.mainStage);
            dialog.centerOnScreen();
            BaseSetInfoAddController controller = loader.getController();
            controller.setDialog(dialog);
            controller.setBaseData("", label, "");
            controller.setResultHandld(handle);
            dialog.show();
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    private void commonModifyV2(Map<String, Object> map, Runnable handle, String label) {
        try {
            if (ObjectUtil.isNull(map)) {
                StageManager.nullWarn("提示", "请选中某一行");
                return;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.BASESET_INFO_DIALOG_V2.fxml()));
            Parent root = loader.load();
            Scene sence = new Scene(root, 600, 300); // 页面大小
            Stage dialog = new Stage();
            dialog.setScene(sence);
            dialog.setTitle(label + "-基础信息-修改");
            dialog.initStyle(StageStyle.UTILITY);
            dialog.initOwner(Main.mainStage);
            dialog.centerOnScreen();
            BaseSetInfoAddController controller = loader.getController();
            controller.setDialog(dialog);
            controller.setBaseDataV2(map.get("id") + "", (String)map.get("content"), (String) map.get("code"));
            controller.setResultHandld(handle);
            dialog.show();
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    private void commonModify(Map<String, Object> map, Runnable handle, String label) {
        try {
            if (ObjectUtil.isNull(map)) {
                StageManager.nullWarn("提示", "请选中某一行");
                return;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.BASESET_INFO_DIALOG.fxml()));
            Parent root = loader.load();
            Scene sence = new Scene(root, 600, 300); // 页面大小
            Stage dialog = new Stage();
            dialog.setScene(sence);
            dialog.setTitle(label + "-基础信息-修改");
            dialog.initStyle(StageStyle.UTILITY);
            dialog.initOwner(Main.mainStage);
            dialog.centerOnScreen();
            BaseSetInfoAddController controller = loader.getController();
            controller.setDialog(dialog);
            controller.setBaseData(map.get("id") + "", label, (String) map.get("content"));
            controller.setResultHandld(handle);
            dialog.show();
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    //维修产品状态
    public void refreshStatusData() {
        Map<String, Object> result = dao.queryByExample("CM_BASE_PT_STATUS","",0,8);
        List<Map<String,Object>> dataList = (List<Map<String, Object>>)result.get("data");
        ObservableList<Map<String,Object>> items = FXCollections.observableArrayList(dataList);
        this.pageSet_status.setPageCount(ObjectUtil.getInt(result.get("total"))<1?1:ObjectUtil.getInt(result.get("total")));
        this.status_table.getSelectionModel().clearSelection();
        this.status_table.setItems(items);
        this.status_table.refresh();
        this.pageSet_status.setCurrentPageIndex(0);
    }

/*    public void statusReset(ActionEvent actionEvent) {
        this.status_text_content.clear();
    }

    public void statusQuery(ActionEvent actionEvent) {
        refreshStatusData();
    }*/

    public void statusAdd(ActionEvent actionEvent) {
        commonAdd(() -> {
            refreshStatusData();
        }, "维修产品状态");
    }

    public void statusModify(ActionEvent actionEvent) {
        commonModify((Map) this.status_table.getSelectionModel().getSelectedItem(), () -> {
            refreshStatusData();
        }, "维修产品状态");
    }

    public void statusDelete(ActionEvent actionEvent) {
        Map<String, Object> map = (Map) this.status_table.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            StageManager.nullWarn("提示", "请选中某一行");
            return;
        }
        if (StageManager.deleteTrue()) {
            this.dao.commonDeleteById("CM_BASE_PT_STATUS", (Long) map.get("id") + "");
            refreshStatusData();
        }
    }


    //故障现象
    public void refreshShowData() {
        String content = show_text_content.getText();
        Map<String, Object> result = dao.queryByExampleV2("CM_BASE_FAULT_SHOW",content,this.show_text_code.getText(),0,8);
        List<Map<String,Object>> dataList = (List<Map<String, Object>>)result.get("data");
        ObservableList<Map<String,Object>> items = FXCollections.observableArrayList(dataList);
        this.pageSet_show.setPageCount(ObjectUtil.getInt(result.get("total"))<1?1:ObjectUtil.getInt(result.get("total")));
        this.show_table.getSelectionModel().clearSelection();
        this.show_table.setItems(items);
        this.show_table.refresh();
        this.pageSet_show.setCurrentPageIndex(0);
    }

    public void showReset(ActionEvent actionEvent) {
        this.show_text_content.clear();
        this.show_text_code.clear();
    }

    public void showQuery(ActionEvent actionEvent) {
        refreshShowData();
    }

    public void showAdd(ActionEvent actionEvent) {
        commonAddV2(() -> {
            refreshShowData();
        }, "故障现象");
    }

    public void showModify(ActionEvent actionEvent) {
        commonModifyV2((Map) this.show_table.getSelectionModel().getSelectedItem(), () -> {
            refreshShowData();
        }, "故障现象");
    }

    public void showDelete(ActionEvent actionEvent) {
        Map<String, Object> map = (Map) this.show_table.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            StageManager.nullWarn("提示", "请选中某一行");
            return;
        }
        if (StageManager.deleteTrue()) {
            this.dao.commonDeleteById("CM_BASE_FAULT_SHOW", (Long) map.get("id") + "");
            refreshShowData();
        }
    }

    //维修措施
    public void refreshMethodData() {
        Map<String, Object> result = dao.queryByExample("CM_BASE_MAINTAIN_METHOD","",0,8);
        List<Map<String,Object>> dataList = (List<Map<String, Object>>)result.get("data");
        ObservableList<Map<String,Object>> items = FXCollections.observableArrayList(dataList);
        this.pageSet_method.setPageCount(ObjectUtil.getInt(result.get("total"))<1?1:ObjectUtil.getInt(result.get("total")));
        this.method_table.getSelectionModel().clearSelection();
        this.method_table.setItems(items);
        this.method_table.refresh();
        this.pageSet_method.setCurrentPageIndex(0);


    }

    public void methodAdd(ActionEvent actionEvent) {
        commonAdd(() -> {
            refreshMethodData();
        }, "维修措施");
    }

    public void methodModify(ActionEvent actionEvent) {
        commonModify((Map) this.method_table.getSelectionModel().getSelectedItem(), () -> {
            refreshMethodData();
        }, "维修措施");
    }

    public void methodDelete(ActionEvent actionEvent) {
        Map<String, Object> map = (Map) this.method_table.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            StageManager.nullWarn("提示", "请选中某一行");
            return;
        }
        if (StageManager.deleteTrue()) {
            this.dao.commonDeleteById("CM_BASE_MAINTAIN_METHOD", map.get("id") + "");
            refreshMethodData();
        }
    }

    //处理结果
    public void refreshResultData() {
        Map<String, Object> result = dao.queryByExample("CM_BASE_HANDLE_RESULT","",0,8);
        List<Map<String,Object>> dataList = (List<Map<String, Object>>)result.get("data");
        ObservableList<Map<String,Object>> items = FXCollections.observableArrayList(dataList);
        this.pageSet_result.setPageCount(ObjectUtil.getInt(result.get("total"))<1?1:ObjectUtil.getInt(result.get("total")));
        this.result_table.getSelectionModel().clearSelection();
        this.result_table.setItems(items);
        this.result_table.refresh();
        this.pageSet_result.setCurrentPageIndex(0);
    }

    public void resultAdd(ActionEvent actionEvent) {
        commonAdd(() -> {
            refreshResultData();
        }, "处理结果");
    }

    public void resultModify(ActionEvent actionEvent) {
        commonModify((Map) this.result_table.getSelectionModel().getSelectedItem(), () -> {
            refreshResultData();
        }, "处理结果");
    }

    public void resultDelete(ActionEvent actionEvent) {
        Map<String, Object> map = (Map) this.result_table.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            StageManager.nullWarn("提示", "请选中某一行");
            return;
        }
        if (StageManager.deleteTrue()) {
            this.dao.commonDeleteById("CM_BASE_HANDLE_RESULT", map.get("id") + "");
            refreshResultData();
        }
    }

    @FXML
    void methodImport(ActionEvent event) {

    }


    @FXML
    void exportFaultShow(ActionEvent event) {
        List<Map<String, Object>> dataList = this.dao.queryByExampleV2("CM_BASE_FAULT_SHOW", this.show_text_content.getText(),this.show_code.getText());
        ExcelUtil.chooseDirectoryToWriteFromDataBase("故障现象", BaseFaultShowExportObj.getHeadMap(), dataList, BaseFaultShowExportObj.class);
    }

    @FXML
    void importFaultShow(ActionEvent event) {
        List<BaseFaultShowImportObj> data = ExcelUtil.chooseFileToRead(BaseFaultShowImportObj.getHeadMap(), BaseFaultShowImportObj.class);
        if (!data.isEmpty()) {
            int count = 0;
            for (int i = 0; i < data.size(); i++) {
                if(!dao.isExistSome("CM_BASE_FAULT_SHOW",data.get(i).getContent(),data.get(i).getCode())) {
                    this.dao.addV2("CM_BASE_FAULT_SHOW", data.get(i).getContent(), data.get(i).getCode());
                    count++;
                }
            }
            logger.info("导入故障数据成功 count = {}", count);
            StageManager.infoWarn(String.format("导入成功,已自动过滤重复数据"));
            refreshShowData();
        }
    }

    @FXML
    void exportFaultShowModel(ActionEvent event) {
  //      List<Map<String, Object>> dataList = this.dao.queryByExampleV2("CM_BASE_FAULT_SHOW", this.show_text_content.getText(),this.show_code.getText());
        ExcelUtil.chooseDirectoryToWriteFromDataBase("故障现象", BaseFaultShowExportObj.getHeadMapModel(), new ArrayList(), BaseFaultShowExportObj.class);
    }
}
