package com.xbky.circuitManger.view.baseSetPt;

import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.dao.ProductTypeDao;
import com.xbky.circuitManger.entity.ProductType;
import com.xbky.circuitManger.export.ProductTypeExportObj;
import com.xbky.circuitManger.utils.DialogUtil;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

public class BaseSetPtController implements Initializable {
    private static final Logger log = getLogger(BaseSetPtController.class);

    @FXML
    public TextField qTfCategory;
    @FXML
    public TextField qTfModel;
    @FXML
    public TextField qTfBrand;
    @FXML
    public Pagination pageSet;
    @FXML
    public TableColumn updateTime;
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



    private static Scene sence = null;

    ProductTypeDao prodectTypeService = new  ProductTypeDao();



    public void deleteUsers(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.id.setCellValueFactory(new MapValueFactory<String>("id"));
        this.category.setCellValueFactory(new MapValueFactory<String>("category"));
        this.model.setCellValueFactory(new MapValueFactory<String>("model"));
        this.brand.setCellValueFactory(new MapValueFactory<String>("brand"));
        this.updateTime.setCellValueFactory(new MapValueFactory<String>("update_time"));
        this.pageSet.setPageFactory(pageIndex -> createPage(pageIndex));
        refreshData();
    }

    public void refreshData(){
        ObservableList<Map<String,Object>> list = FXCollections.observableArrayList();
        Map<String,Object> map =  prodectTypeService.queryByExample(getSearchParam(),0,10);
        List<Map<String,Object>> dataList = (List<Map<String,Object>>)map.get("data");
        list.addAll(dataList);
        this.userTable.getSelectionModel().clearSelection();
        this.userTable.setItems(list);
        this.userTable.refresh();
        this.pageSet.setCurrentPageIndex(0);
        this.pageSet.setPageCount(ObjectUtil.getInt(map.get("total")));

    }

    public TableView<Map<String,Object>> createPage(int pageIndex) {
        Map<String, Object> result = prodectTypeService.queryByExample(getSearchParam(),pageIndex,10);
        List<Map<String,Object>> dataList = (List<Map<String, Object>>)result.get("data");
        ObservableList<Map<String,Object>> items = FXCollections.observableArrayList(dataList);
        this.userTable.getSelectionModel().clearSelection();
        this.userTable.setItems(items);
        this.userTable.refresh();
        return userTable;
    }

    /**
     * 添加数据
     * @param actionEvent
     * @throws IOException
     */
    public void addData(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.BASESET_PT_DIALOG.fxml()));
        Parent root = loader.<Parent>load();
        sence = new Scene(root, 500, 600); // 页面大小
       // StageManager.initStyle(dialog);
        Stage dialog = new Stage();
        dialog.setTitle("产品类别型号-添加");
        dialog.setScene(sence);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initOwner(Main.mainStage);
        dialog.centerOnScreen();
        BaseSetPtAddController  controller = loader.getController();
        controller.setDialog(dialog);
        controller.setResultHandld(()->{refreshData();});
        dialog.show();
    }


    /**
     * 删除选中数据
     * @param actionEvent
     * @throws IOException
     */
    public void deleteData(ActionEvent actionEvent) {
        Map<String,Object> map = (Map)userTable.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            StageManager.nullWarn("请选中某一行");
            return;
        }
        if(StageManager.deleteTrue()) {
            prodectTypeService.deleteById((Long) map.get("id"));
            refreshData();
        }
    }

    /**
     * 修改选中数据
     * @param actionEvent
     * @throws IOException
     */
    public void modifyData(ActionEvent actionEvent) throws IOException {
        Map<String,Object> map = (Map)userTable.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            StageManager.nullWarn("请选中某一行");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.BASESET_PT_DIALOG.fxml()));
        Parent root = loader.<Parent>load();
        sence = new Scene(root, 500, 600); // 页面大小
        // StageManager.initStyle(dialog);
        Stage dialog = new Stage();
        dialog.setTitle("产品类别型号-修改");
        dialog.setScene(sence);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initOwner(Main.mainStage);
        dialog.centerOnScreen();
        BaseSetPtAddController  controller = (BaseSetPtAddController) loader.getController();
        controller.setDialog(dialog);
        controller.setBaseData(new ProductType((Long)map.get("id"),(String)map.get("category"),(String)map.get("model"),(String)map.get("brand")));
        controller.setResultHandld(()->{refreshData();});
        dialog.show();
    }

    public void resetData(ActionEvent actionEvent) {
        this.qTfCategory.clear();
        this.qTfModel.clear();
        this.qTfBrand.clear();
    }

    public void queryData(ActionEvent actionEvent) {
        refreshData();
    }

    private ProductType getSearchParam() {
        ProductType pt = new ProductType();
        pt.setCategory(this.qTfCategory.getText());
        pt.setModel(this.qTfModel.getText());
        pt.setBrand(this.qTfBrand.getText());
        return pt;
    }

    @FXML
    void exportSearchData(ActionEvent event) {
        ProductType searchParam = getSearchParam();
        List<Map<String,Object>> dataList = this.prodectTypeService.queryByExample(searchParam);
        ExcelUtil.chooseDirectoryToWriteFromDataBase(FxmlView.BASESET_PT.title(), ProductTypeExportObj.getHeadMap(), dataList, ProductTypeExportObj.class);
    }
}
