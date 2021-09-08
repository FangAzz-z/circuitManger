package com.xbky.circuitManger.view.check;

import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.dao.*;
import com.xbky.circuitManger.entity.CheckMaintainRecord;
import com.xbky.circuitManger.export.CheckMaintainRecordExportObj;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CheckMaintainController implements Initializable {

    @FXML
    public TableColumn id;
    @FXML
    public TableColumn maintainId;
    @FXML
    public TableColumn receiveDate;
    @FXML
    public TableColumn completeDate;
    @FXML
    public TableColumn maintainCardNo;
    @FXML
    public TableColumn maintainCardModel;
    @FXML
    public TableColumn maintainCardBrand;
    @FXML
    public TableColumn maintainCardCategory;
    @FXML
    public TableColumn maintainUser;
    @FXML
    public TableColumn wxStatus;
    @FXML
    public TableColumn wxShow;
    @FXML
    public TableColumn wxMethod;
    @FXML
    public TableColumn wxResult;
    @FXML
    public TableColumn maintainDesc;
    @FXML
    public TableColumn maintainFitting;

    @FXML
    public ComboBox queryModel;
    @FXML
    public ComboBox queryCategory;
    @FXML
    public ComboBox queryBrand;
    @FXML
    public ComboBox queryUser;
    @FXML
    public ComboBox queryStatus;
    @FXML
    public TableView userTable;
    @FXML
    public DatePicker queryReceiveDateStart;
    @FXML
    public DatePicker queryReceiveDateEnd;
    @FXML
    public DatePicker queryDoneDateStart;
    @FXML
    public DatePicker queryDoneDateEnd;
    @FXML
    public TextField queryMaintainOrder;
    @FXML
    public TextField queryCardNo;
    @FXML
    public Pagination pageSet;

    CheckMaintainRecordDao dao = new CheckMaintainRecordDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.id.setCellValueFactory(new MapValueFactory<String>("id"));
        this.maintainId.setCellValueFactory(new MapValueFactory<String>("maintain_id"));
        this.receiveDate.setCellValueFactory(new  MapValueFactory<String>("receive_date"));
        this.completeDate.setCellValueFactory(new MapValueFactory<String>("complete_date"));
        this.maintainCardNo.setCellValueFactory(new MapValueFactory<String>("maintain_card_no"));
        this.maintainCardModel.setCellValueFactory(new MapValueFactory<String>("maintain_card_model"));
        this.maintainCardBrand.setCellValueFactory(new MapValueFactory<String>("maintain_card_brand"));
        this.maintainCardCategory.setCellValueFactory(new MapValueFactory<>("maintain_card_category"));
        this.maintainUser.setCellValueFactory(new MapValueFactory<>("maintain_user"));
        this.wxStatus.setCellValueFactory(new MapValueFactory<>("wx_status"));
        this.wxShow.setCellValueFactory(new MapValueFactory<>("wx_show"));
        this.wxMethod.setCellValueFactory(new MapValueFactory<>("wx_method"));
        this.wxResult.setCellValueFactory(new MapValueFactory<>("wx_result"));
        this.maintainDesc.setCellValueFactory(new MapValueFactory<>("maintain_desc"));
        this.maintainFitting.setCellValueFactory(new MapValueFactory<>("maintain_fitting"));
        this.pageSet.setPageFactory(pageIndex -> createPage(pageIndex));
        refreshData();

        List<String> statusList =  dao.commonQueryAll("CM_BASE_PT_STATUS").stream().map(a->((String)a.get("content"))).collect(Collectors.toList());
        List<String> userName = dao.commonQueryAll("CM_MAINTAIN_USER").stream().map(a->(a.get("department")+((String)a.get("name")))).collect(Collectors.toList());
        List<String> category = dao.commonQueryAll("CM_PRODUCT_TYPE").stream().map(a->((String)a.get("category"))).collect(Collectors.toList());
        List<String> model = dao.commonQueryAll("CM_PRODUCT_TYPE").stream().map(a->((String)a.get("model"))).collect(Collectors.toList());
        List<String> brand = dao.commonQueryAll("CM_PRODUCT_TYPE").stream().map(a->((String)a.get("brand"))).collect(Collectors.toList());
        queryModel.setItems(FXCollections.observableArrayList(model));
        queryCategory.setItems(FXCollections.observableArrayList(category));
        queryBrand.setItems(FXCollections.observableArrayList(brand));
        queryUser.setItems(FXCollections.observableArrayList(userName));
        queryStatus.setItems(FXCollections.observableArrayList(statusList));
    }

    public void addData(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.CHECK_MAINTAIN_DIALOG.fxml()));
        Parent root = loader.load();
        Scene scene = new Scene(root, 750, 600); // 页面大小
        // StageManager.initStyle(dialog);
        Stage dialog = new Stage();
        dialog.setTitle("维修登记单-添加"); // 页面标题
        dialog.setScene(scene);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initOwner(Main.mainStage);
        dialog.centerOnScreen();
        CheckMaintainAddController controller = loader.getController();
        controller.setDialog(dialog);
        controller.setResultHandle(()->{refreshData();});
        dialog.show();
    }

    private void refreshData() {
        ObservableList<Map<String,Object>> list = FXCollections.observableArrayList();
       Map<String,Object> map =  dao.queryByExample(getSearchParam(),0,10);
        List<Map<String,Object>> dataList = (List<Map<String,Object>>)map.get("data");
        list.addAll(dataList);
        this.pageSet.setPageCount(ObjectUtil.getInt(map.get("total")));
        this.userTable.getSelectionModel().clearSelection();
        this.userTable.setItems(list);
        this.userTable.refresh();
        this.pageSet.setCurrentPageIndex(0);

    }

    private TableView<Map<String,Object>> createPage(int pageIndex) {
        Map<String, Object> result = dao.queryByExample(getSearchParam(),pageIndex,10);
        List<Map<String,Object>> dataList = (List<Map<String, Object>>)result.get("data");
        ObservableList<Map<String,Object>> items = FXCollections.observableArrayList(dataList);
        userTable.setItems(items);
        return userTable;
    }

    public void modifyData(ActionEvent actionEvent) {

    }

    public void deleteData(ActionEvent actionEvent) {
        Map<String,Object> map = (Map)userTable.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            StageManager.nullWarn();
            return;
        }
        if(StageManager.deleteTrue()) {
            dao.commonDeleteById("CM_CHECK_MAINTAIN_RECORD", map.get("id") + "");
            refreshData();
        }
    }

    public void reset(ActionEvent actionEvent) {
        queryModel.getSelectionModel().clearSelection();
        queryCategory.getSelectionModel().clearSelection();
        queryBrand.getSelectionModel().clearSelection();
        queryUser.getSelectionModel().clearSelection();
        queryStatus.getSelectionModel().clearSelection();
        queryReceiveDateStart.getEditor().clear();
        queryReceiveDateEnd.getEditor().clear();
        queryDoneDateStart.getEditor().clear();
        queryReceiveDateEnd.getEditor().clear();
        queryMaintainOrder.clear();
    }

    public void query(ActionEvent actionEvent) {
        refreshData();
    }

    private CheckMaintainRecord getSearchParam() {
        CheckMaintainRecord record = new CheckMaintainRecord();
        record.setMaintainId(this.queryMaintainOrder.getText());
        record.setMaintainCardNo(this.queryCardNo.getText());
        record.setReceiveStartTime(this.queryReceiveDateStart.getEditor().getText());
        record.setReceiveEndTime(this.queryReceiveDateEnd.getEditor().getText());
        record.setCompleteStartTime(this.queryDoneDateStart.getEditor().getText());
        record.setCompleteEndTime(this.queryDoneDateEnd.getEditor().getText());
        if(this.queryModel.getSelectionModel().getSelectedItem()!=null) {
            record.setMaintainCardModel(this.queryModel.getSelectionModel().getSelectedItem().toString());
        }
        if(this.queryBrand.getSelectionModel().getSelectedItem()!=null) {
            record.setMaintainCardBrand(this.queryBrand.getSelectionModel().getSelectedItem().toString());
        }
        if(this.queryCategory.getSelectionModel().getSelectedItem()!=null) {
            record.setMaintainCardCategory(this.queryCategory.getSelectionModel().getSelectedItem().toString());
        }
        if(this.queryUser.getSelectionModel().getSelectedItem()!=null) {
            record.setMaintainUser(this.queryUser.getSelectionModel().getSelectedItem().toString());
        }
        if(this.queryStatus.getSelectionModel().getSelectedItem()!=null) {
            record.setWxStatus(this.queryStatus.getSelectionModel().getSelectedItem().toString());
        }
        return record;
    }

    @FXML
    void exportSearchData(ActionEvent event) {
        CheckMaintainRecord record = getSearchParam();
        List<Map<String,Object>> dataList = this.dao.queryByExample(record);

        ExcelUtil.chooseDirectoryToWriteFromDataBase(FxmlView.CHECK_MAINTAIN.title(), CheckMaintainRecordExportObj.getHeadMap(),dataList, CheckMaintainRecordExportObj.class);

    }
}
