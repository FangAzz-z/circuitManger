package com.xbky.circuitManger.view.check;

import com.xbky.circuitManger.dao.FittingIntoInfoDao;
import com.xbky.circuitManger.entity.FittingIntoInfo;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Function;

public class CheckFittingChooseController  implements Initializable {

    @FXML
    public TextField tfNo;
    @FXML
    public TextField tfName;
    @FXML
    public TextField tfModel;
    @FXML
    public TextField tfFactory;

    @FXML
    public TableColumn<Map, String> no;
    @FXML
    public TableColumn<Map, String> model;
    @FXML
    public TableColumn<Map, String> name;
    @FXML
    public TableColumn<Map, String> factory;
    @FXML
    public TableView userTable;

    private  static Stage dialog = null;
    private  static Function resultHandle = null;


    FittingIntoInfoDao dao = new FittingIntoInfoDao();

    public  Stage getDialog() {
        return dialog;
    }

    public  void setDialog(Stage dialog) {
        CheckFittingChooseController.dialog = dialog;
    }

    public  Function getResultHandle() {
        return resultHandle;
    }

    public  void setResultHandle(Function resultHandle) {
        CheckFittingChooseController.resultHandle = resultHandle;
    }

    public void cancelSubmit(ActionEvent actionEvent) {
        if(dialog!=null) {
            dialog.close();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.no.setCellValueFactory(new MapValueFactory<String>("fitting_no"));
        this.name.setCellValueFactory(new MapValueFactory<String>("fitting_name"));
        this.model.setCellValueFactory(new MapValueFactory<String>("fitting_model"));
        this.factory.setCellValueFactory(new MapValueFactory<String>("factory"));
        refreshData();

        //行选择事件
        userTable.setRowFactory( tv -> {
            TableRow<Map<String,Object>> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Map<String,Object> map = row.getItem();
                    handleChoose(map);
                }
            });
            return row ;
        });
    }

    public void refreshData(){
        ObservableList<Map<String,Object>> list = FXCollections.observableArrayList();
        List<Map<String,Object>> dataList =  dao.queryByExample(getSearchParam());
        list.addAll(dataList);
        this.userTable.getSelectionModel().clearSelection();
        this.userTable.setItems(list);
        this.userTable.refresh();
    }

    public void submitData(ActionEvent actionEvent) {
        Map<String,Object> map = (Map)userTable.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            StageManager.nullWarn();
            return;
        }
        if(resultHandle!=null){
            handleChoose(map);
        }
    }

    /**
     * 选择处理
     * @param map
     */
    private void handleChoose(Map<String,Object> map){
        resultHandle.apply(map);
        if(dialog!=null){
            dialog.close();
        }
    }

    public void reset(ActionEvent actionEvent) {
        this.tfNo.clear();
        this.tfName.clear();
        this.tfModel.clear();
        this.tfFactory.clear();
    }

    public void query(ActionEvent actionEvent) {
        refreshData();
    }

    public FittingIntoInfo getSearchParam(){
        FittingIntoInfo fii = new FittingIntoInfo();
        fii.setFittingNo(this.tfNo.getText());
        fii.setFittingName(this.tfName.getText());
        fii.setFittingModel(this.tfModel.getText());
        fii.setFactory(this.tfFactory.getText());
        return fii;
    }
}
