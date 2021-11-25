package com.xbky.circuitManger.view.check;

import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.dao.*;
import com.xbky.circuitManger.entity.CheckMaintainRecord;
import com.xbky.circuitManger.export.CheckMaintainRecordExportObj;
import com.xbky.circuitManger.utils.ExcelUtil;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.view.common.FxmlView;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class CheckMaintainController implements Initializable {
    Logger logger = LoggerFactory.getLogger(CheckMainListController.class);

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
    ProductTypeDao ptDao = new ProductTypeDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.id.setCellValueFactory(new MapValueFactory<String>("id"));
        this.maintainId.setCellValueFactory(new MapValueFactory<String>("maintain_id"));
        this.receiveDate.setCellValueFactory(new  MapValueFactory<String>("receive_date"));
        this.completeDate.setCellValueFactory(new MapValueFactory<String>("complete_date"));
        this.maintainCardNo.setCellValueFactory(new MapValueFactory<String>("maintain_card_no"));
        this.maintainCardModel.setCellValueFactory(new MapValueFactory<String>("maintain_card_model"));
        this.maintainCardBrand.setCellValueFactory(new MapValueFactory<String>("maintain_card_brand"));
        this.maintainCardCategory.setCellValueFactory(new MapValueFactory<String>("maintain_card_category"));
        this.maintainUser.setCellValueFactory(new MapValueFactory<String>("maintain_user"));
        this.wxStatus.setCellValueFactory(new MapValueFactory<String>("wx_status"));
        this.wxShow.setCellValueFactory(new MapValueFactory<String>("wx_show"));
        this.wxMethod.setCellValueFactory(new MapValueFactory<String>("wx_method"));
        this.wxResult.setCellValueFactory(new MapValueFactory<String>("wx_result"));
        this.maintainDesc.setCellValueFactory(new MapValueFactory<String>("maintain_desc"));
        this.maintainFitting.setCellValueFactory(new MapValueFactory<String>("maintain_fitting"));
        this.pageSet.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                return CheckMaintainController.this.createPage(pageIndex);
            }
        });
        refreshData();

        List<String> statusList = new ArrayList<String>();
        List<Map<String,Object>> statusStrList = dao.commonQueryAll("CM_BASE_PT_STATUS");
        for (int i = 0; i < statusStrList.size(); i++) {
            statusList.add((String)statusStrList.get(i).get("content"));
        }
        List<String> userName = new ArrayList<String>();
        List<Map<String,Object>> userNameList = dao.commonQueryAll("CM_MAINTAIN_USER");
        for (int i = 0; i < userNameList.size(); i++) {
            Map<String, Object> a = userNameList.get(i);
            String str = a.get("department")+((String)a.get("name"));
            userName.add(str);
        }
        queryUser.setItems(FXCollections.observableArrayList(userName));
        queryStatus.setItems(FXCollections.observableArrayList(statusList));

        //类别 品牌 型号 联动
        List<String> category = ptDao.queryAllCategory();
        queryCategory.setItems(FXCollections.observableArrayList(category));
        queryCategory.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                List<String> brand = ptDao.queryBrandByCategory(ObjectUtil.getString(newValue));
                queryBrand.setItems(FXCollections.observableArrayList(brand));
            }
        });
        queryBrand.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                List<String> model = ptDao.queryModelByBrand(CheckMaintainController.this.queryCategory.getSelectionModel().getSelectedItem().toString(), ObjectUtil.getString(newValue));
                queryModel.setItems(FXCollections.observableArrayList(model));
            }
        });

        //行选择事件
        userTable.setRowFactory(new Callback() {
            @Override
            public Object call(Object tv) {
                final TableRow<Map<String, Object>> row = new TableRow<Map<String, Object>>();
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getClickCount() == 2 && (!row.isEmpty())) {
                            Map<String, Object> map = row.getItem();
                            CheckMaintainController.this.detailData(map);
                        }
                    }
                });
                return row;
            }
        });
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
        controller.setResultHandle(        new Runnable() {
            @Override
            public void run() {
                refreshData();
            }
        });
        controller.createMaintainId();
        dialog.show();
    }

    public void detailData(Map<String,Object> map) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/mainCheckList.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 550, 600); // 页面大小
            // StageManager.initStyle(dialog);
            Stage dialog = new Stage();
            dialog.setTitle("详细"); // 页面标题
            dialog.setScene(scene);
            dialog.initStyle(StageStyle.UTILITY);
            dialog.initOwner(Main.mainStage);
            dialog.centerOnScreen();
            CheckMainListController controller = loader.getController();
            controller.setDialog(dialog);
            controller.setListItems(map);
            dialog.show();
        } catch (Exception e) {
            logger.error("",e);
        }
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

    }

    private TableView<Map<String,Object>> createPage(int pageIndex) {
        Map<String, Object> result = dao.queryByExample(getSearchParam(),pageIndex,10);
        List<Map<String,Object>> dataList = (List<Map<String, Object>>)result.get("data");
        ObservableList<Map<String,Object>> items = FXCollections.observableArrayList(dataList);
        userTable.setItems(items);
        return userTable;
    }

    public void modifyData(ActionEvent actionEvent) throws IOException {
        Map<String,Object> map = (Map)userTable.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            StageManager.nullWarn("请选中某一行");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.CHECK_MAINTAIN_DIALOG.fxml()));
        Parent root = loader.load();
        Scene scene = new Scene(root, 750, 600); // 页面大小
        // StageManager.initStyle(dialog);
        Stage dialog = new Stage();
        dialog.setTitle("维修登记单-修改"); // 页面标题
        dialog.setScene(scene);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initOwner(Main.mainStage);
        dialog.centerOnScreen();
        CheckMaintainAddController controller = loader.getController();
        controller.setDialog(dialog);
        controller.setResultHandle(        new Runnable() {
            @Override
            public void run() {
                refreshData();
            }
        });
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
