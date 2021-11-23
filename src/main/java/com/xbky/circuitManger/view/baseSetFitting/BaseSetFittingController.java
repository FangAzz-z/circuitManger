package com.xbky.circuitManger.view.baseSetFitting;

import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.dao.FittingIntoInfoDao;
import com.xbky.circuitManger.entity.FittingIntoInfo;
import com.xbky.circuitManger.export.FittingIntoInfoExportObj;
import com.xbky.circuitManger.importexcel.FittingIntoInfoImportObj;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static com.xbky.circuitManger.view.common.StageManager.nullWarn;

public class BaseSetFittingController implements Initializable {


    Logger logger = LoggerFactory.getLogger(BaseSetFittingController.class);

    @FXML
    public TextField tfNo;
    @FXML
    public TextField tfName;
    @FXML
    public TextField tfModel;
    @FXML
    public TextField tfFactory;
    @FXML
    public TextField tfPackaging;
    @FXML
    public TextField tfUnit;


    @FXML
    public TableView userTable;
    @FXML
    public TableColumn id;
    @FXML
    public TableColumn no;
    @FXML
    public TableColumn name;
    @FXML
    public TableColumn model;
    @FXML
    public TableColumn packaging;
    @FXML
    public TableColumn factory;
    @FXML
    public TableColumn unit;

    @FXML
    public Pagination pageSet;



    FittingIntoInfoDao dao = new FittingIntoInfoDao();
    private  static Stage dialog = null;
    private  static Runnable resultHandle = null;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.id.setCellValueFactory(new MapValueFactory<String>("id"));
        this.no.setCellValueFactory(new MapValueFactory<String>("fitting_no"));
        this.name.setCellValueFactory(new MapValueFactory<String>("fitting_name"));
        this.model.setCellValueFactory(new MapValueFactory<String>("fitting_model"));
        this.packaging.setCellValueFactory(new MapValueFactory<String>("packaging"));
        this.factory.setCellValueFactory(new MapValueFactory<String>("factory"));
        this.unit.setCellValueFactory(new MapValueFactory<String>("unit"));
        this.pageSet.setPageFactory(pageIndex -> createPage(pageIndex));
        refreshData();
    }


    public void refreshData(){
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
    public TableView<Map<String,Object>> createPage(int pageIndex) {
        Map<String, Object> result = dao.queryByExample(getSearchParam(),pageIndex,10);
        List<Map<String,Object>> dataList = (List<Map<String, Object>>)result.get("data");
        ObservableList<Map<String,Object>> items = FXCollections.observableArrayList(dataList);
        this.userTable.getSelectionModel().clearSelection();
        this.userTable.setItems(items);
        this.userTable.refresh();
        return userTable;
    }

    public void reset(ActionEvent actionEvent) {
        this.tfNo.clear();
        this.tfName.clear();
        this.tfModel.clear();
        this.tfFactory.clear();
        this.tfUnit.clear();
    }

    public void query(ActionEvent actionEvent) {
        refreshData();
    }

    public void addData(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.BASESET_FITTING_DIALOG.fxml()));
        Parent root = loader.load();
        Scene scene = new Scene(root, 500, 500); // 页面大小
        Stage dialog = new Stage();
        dialog.setScene(scene);
        dialog.setTitle("配件入库信息-添加");
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initOwner(Main.mainStage);
        dialog.centerOnScreen();
        BaseSetFittingAddController controller = loader.getController();
        controller.setDialog(dialog);
        controller.setResultHandle(new Runnable() {
            @Override
            public void run() {
                refreshData();
            }
        });
        dialog.show();
    }

    public void modifyData(ActionEvent actionEvent) throws IOException {
        Map<String,Object> map = (Map)userTable.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            StageManager.nullWarn();
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.BASESET_FITTING_DIALOG.fxml()));
        Parent root = loader.load();
        Scene scene = new Scene(root, 500, 600); // 页面大小
        Stage dialog = new Stage();
        dialog.setScene(scene);
        dialog.setTitle("配件入库信息-修改");
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initOwner(Main.mainStage);
        dialog.centerOnScreen();
        BaseSetFittingAddController  controller = loader.getController();
        controller.setDialog(dialog);
        controller.setBaseData(new FittingIntoInfo((Long)map.get("id"),(String)map.get("fitting_no"),(String)map.get("fitting_name"),(String)map.get("fitting_model"),(String)map.get("packaging"),(String)map.get("factory"),(String)map.get("unit")));
        controller.setResultHandle(new Runnable() {
            @Override
            public void run() {
                refreshData();
            }
        });
        dialog.show();
    }

    public FittingIntoInfo getSearchParam(){
        FittingIntoInfo fii = new FittingIntoInfo();
        fii.setFittingNo(this.tfNo.getText());
        fii.setFittingName(this.tfName.getText());
        fii.setFittingModel(this.tfModel.getText());
        fii.setPackaging(this.tfPackaging.getText());
        fii.setFactory(this.tfFactory.getText());
        fii.setUnit(this.tfUnit.getText());
        return fii;
    }

    public void deleteData(ActionEvent actionEvent) {
        Map<String,Object> map = (Map)userTable.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            nullWarn();
            return;
        }
        if(StageManager.deleteTrue()) {
            dao.commonDeleteById("CM_FITTING_INTO_INFO", map.get("id") + "");
            refreshData();
        }
    }

    @FXML
    void exportFaultShow(ActionEvent event) {
        List<Map<String, Object>> dataList = this.dao.queryByExample(getSearchParam());
        ExcelUtil.chooseDirectoryToWriteFromDataBase("配件入库信息", FittingIntoInfoExportObj.getHeadMap(), dataList, FittingIntoInfoExportObj.class);
    }
/*
    private String fittingNo;

    private String fittingName;

    private String fittingModel;

    private String packaging;

    private String factory;

    private String unit;
 */
    @FXML
    void importFaultShow(ActionEvent event) {
        List<FittingIntoInfoImportObj> data = ExcelUtil.chooseFileToRead(FittingIntoInfoImportObj.getHeadMap(), FittingIntoInfoImportObj.class);
        if (!data.isEmpty()) {
            int count = 0;
            for (int i = 0; i < data.size(); i++) {
                FittingIntoInfoImportObj obj = data.get(i);
                FittingIntoInfo fii = new FittingIntoInfo(obj.getFittingNo(),obj.getFittingName(),obj.getFittingModel(),obj.getPackaging(),obj.getFactory(),obj.getUnit());
                if(!this.dao.isExistSome(fii)) {
                    this.dao.add(fii);
                }
            }
            logger.info("导入故障数据成功 count = {}", count);
            StageManager.infoWarn(String.format("导入成功,已自动过滤重复数据!"));
            refreshData();
        }
    }

    @FXML
    void exportFaultShowModel(ActionEvent event) {
        //      List<Map<String, Object>> dataList = this.dao.queryByExampleV2("CM_BASE_FAULT_SHOW", this.show_text_content.getText(),this.show_code.getText());
        ExcelUtil.chooseDirectoryToWriteFromDataBase("配件入库信息", FittingIntoInfoExportObj.getHeadMapModel(), new ArrayList(), FittingIntoInfoExportObj.class);
    }

}
