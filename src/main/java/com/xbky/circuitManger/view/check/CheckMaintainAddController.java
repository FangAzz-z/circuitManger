package com.xbky.circuitManger.view.check;

import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.dao.CheckMaintainRecordDao;
import com.xbky.circuitManger.entity.CheckMaintainRecord;
import com.xbky.circuitManger.utils.ImageUtil;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.view.baseSetUser.BaseSetUserAddController;
import com.xbky.circuitManger.view.common.FxmlView;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CheckMaintainAddController implements Initializable {

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
    public ComboBox queryShow;
    @FXML
    public ComboBox queryMethod;
    @FXML
    public ComboBox queryResult;
    @FXML
    public TextArea taMaintainDesc;
    @FXML
    public TextArea taMaintainFitting;
    @FXML
    public DatePicker dpReceiveDate;
    @FXML
    public DatePicker dpCompleteDate;
    @FXML
    public TextField tfMaintainCardNo;
    @FXML
    public TextField tfId;

    CheckMaintainRecordDao dao = new CheckMaintainRecordDao();

    private  static Stage dialog = null;
    private  static Runnable resultHandle = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> statusList =  dao.commonQueryAll("CM_BASE_PT_STATUS").stream().map(a->((String)a.get("content"))).collect(Collectors.toList());
        List<String> showList =  dao.commonQueryAll("CM_BASE_FAULT_SHOW").stream().map(a->((String)a.get("content"))).collect(Collectors.toList());
        List<String> methodList =  dao.commonQueryAll("CM_BASE_MAINTAIN_METHOD").stream().map(a->((String)a.get("content"))).collect(Collectors.toList());
        List<String> resultList =  dao.commonQueryAll("CM_BASE_HANDLE_RESULT").stream().map(a->((String)a.get("content"))).collect(Collectors.toList());
        List<String> userName = dao.commonQueryAll("CM_MAINTAIN_USER").stream().map(a->(a.get("department")+((String)a.get("name")))).collect(Collectors.toList());

        List<String> category = dao.commonQueryAll("CM_PRODUCT_TYPE").stream().map(a->((String)a.get("category"))).collect(Collectors.toList());
        List<String> model = dao.commonQueryAll("CM_PRODUCT_TYPE").stream().map(a->((String)a.get("model"))).collect(Collectors.toList());
        List<String> brand = dao.commonQueryAll("CM_PRODUCT_TYPE").stream().map(a->((String)a.get("brand"))).collect(Collectors.toList());
        queryModel.setItems(FXCollections.observableArrayList(model));
        queryCategory.setItems(FXCollections.observableArrayList(category));
        queryBrand.setItems(FXCollections.observableArrayList(brand));
        queryUser.setItems(FXCollections.observableArrayList(userName));
        queryStatus.setItems(FXCollections.observableArrayList(statusList));
        queryShow.setItems(FXCollections.observableArrayList(showList));
        queryMethod.setItems(FXCollections.observableArrayList(methodList));
        queryResult.setItems(FXCollections.observableArrayList(resultList));
    }

    public  Stage getDialog() {
        return dialog;
    }

    public  void setDialog(Stage dialog) {
        CheckMaintainAddController.dialog = dialog;
    }

    public  Runnable getResultHandle() {
        return resultHandle;
    }

    public  void setResultHandle(Runnable resultHandle) {
        CheckMaintainAddController.resultHandle = resultHandle;
    }

    public void cancelSubmit(ActionEvent actionEvent) {
        if(dialog!=null) {
            dialog.close();
        }
    }

    public void submitData(ActionEvent actionEvent) {
        CheckMaintainRecord record = new CheckMaintainRecord();
        if(ObjectUtil.isAllNull(tfMaintainCardNo.getText())){
            StageManager.nullWarn("维修卡编号不能为空");
            return;
        }
        if(ObjectUtil.isAllNull(queryUser.getSelectionModel().getSelectedItem())){
            StageManager.nullWarn("维修人员不能为空");
            return;
        }
        if(ObjectUtil.isAllNull(this.queryCategory.getSelectionModel().getSelectedItem())){
            StageManager.nullWarn("产品类别不能为空");
            return;
        }
        if(ObjectUtil.isAllNull(this.queryModel.getSelectionModel().getSelectedItem())){
            StageManager.nullWarn("产品型号不能为空");
            return;
        }
        if(ObjectUtil.isAllNull(this.queryBrand.getSelectionModel().getSelectedItem())){
            StageManager.nullWarn("维修卡板品牌不能为空");
            return;
        }
        if(this.queryStatus.getSelectionModel().getSelectedItem()!=null) {
            record.setWxStatus(this.queryStatus.getSelectionModel().getSelectedItem().toString());
        }
        if(this.queryUser.getSelectionModel().getSelectedItem()!=null) {
            record.setMaintainUser(this.queryUser.getSelectionModel().getSelectedItem().toString());
        }
        if(this.queryBrand.getSelectionModel().getSelectedItem()!=null) {
            record.setMaintainCardBrand(this.queryBrand.getSelectionModel().getSelectedItem().toString());
        }
        record.setMaintainCardNo(this.tfMaintainCardNo.getText());
        record.setCompleteDate(this.dpCompleteDate.getEditor().getText());
        record.setReceiveDate(this.dpReceiveDate.getEditor().getText());
        if(this.queryResult.getSelectionModel().getSelectedItem()!=null) {
            record.setWxResult(this.queryResult.getSelectionModel().getSelectedItem().toString());
        }
        record.setMaintainDesc(this.taMaintainDesc.getText());
        record.setMaintainFitting(this.taMaintainFitting.getText());
        if(this.queryMethod.getSelectionModel().getSelectedItem()!=null) {
            record.setWxMethod(this.queryMethod.getSelectionModel().getSelectedItem().toString());
        }
        if(this.queryShow.getSelectionModel().getSelectedItem()!=null) {
            record.setWxShow(this.queryShow.getSelectionModel().getSelectedItem().toString());
        }
        if(this.queryModel.getSelectionModel().getSelectedItem()!=null) {
            record.setMaintainCardModel(this.queryModel.getSelectionModel().getSelectedItem().toString());
        }
        if(this.queryCategory.getSelectionModel().getSelectedItem()!=null) {
            record.setMaintainCardCategory(this.queryCategory.getSelectionModel().getSelectedItem().toString());
        }
        if(ObjectUtil.isNull(tfId.getText())) {
            dao.add(record);
        }else{
            record.setId(ObjectUtil.getLong(tfId.getText()));
            dao.modify(record);
        }
        if(dialog!=null) {
            dialog.close();
        }
        if (resultHandle != null) {
            resultHandle.run();
        }
    }

    public void printLabel(ActionEvent actionEvent) {
        if(this.queryStatus.getSelectionModel().getSelectedItem()==null) {
           StageManager.nullWarn("状态不能为空");
        }
        ImageUtil.printToLabel(ObjectUtil.getWxId()+this.queryStatus.getSelectionModel().getSelectedItem().toString());
    }
}
