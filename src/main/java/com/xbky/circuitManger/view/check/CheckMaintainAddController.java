package com.xbky.circuitManger.view.check;

import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.dao.CheckMaintainRecordDao;
import com.xbky.circuitManger.dao.ProductTypeDao;
import com.xbky.circuitManger.entity.CheckMaintainRecord;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.utils.PrintUtil;
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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
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
    @FXML
    public TextField wxId;

    CheckMaintainRecordDao dao = new CheckMaintainRecordDao();
    ProductTypeDao ptDao = new ProductTypeDao();

    private  static Stage dialog = null;
    private  static Runnable resultHandle = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> statusList =  dao.commonQueryAll("CM_BASE_PT_STATUS").stream().map(a->((String)a.get("content"))).collect(Collectors.toList());
        List<String> showList =  dao.commonQueryAll("CM_BASE_FAULT_SHOW").stream().map(a->((String)a.get("content"))).collect(Collectors.toList());
        List<String> methodList =  dao.commonQueryAll("CM_BASE_MAINTAIN_METHOD").stream().map(a->((String)a.get("content"))).collect(Collectors.toList());
        List<String> resultList =  dao.commonQueryAll("CM_BASE_HANDLE_RESULT").stream().map(a->((String)a.get("content"))).collect(Collectors.toList());
        List<String> userName = dao.commonQueryAll("CM_MAINTAIN_USER").stream().map(a->(a.get("department")+((String)a.get("name")))).collect(Collectors.toList());
        queryUser.setItems(FXCollections.observableArrayList(userName));
        queryStatus.setItems(FXCollections.observableArrayList(statusList));
        queryShow.setItems(FXCollections.observableArrayList(showList));
        queryMethod.setItems(FXCollections.observableArrayList(methodList));
        queryResult.setItems(FXCollections.observableArrayList(resultList));

        //类别 品牌 型号 联动
        List<String> category = ptDao.queryAllCategory();
        queryCategory.setItems(FXCollections.observableArrayList(category));
        queryCategory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            List<String> brand = ptDao.queryBrandByCategory(ObjectUtil.getString(newValue));
            queryBrand.setItems(FXCollections.observableArrayList(brand));
        });
        queryBrand.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            List<String> model = ptDao.queryModelByBrand(this.queryCategory.getSelectionModel().getSelectedItem().toString(),ObjectUtil.getString(newValue));
            queryModel.setItems(FXCollections.observableArrayList(model));
        });
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
        if(this.queryStatus.getSelectionModel().getSelectedItem()==null) {
            StageManager.nullWarn("状态不能为空");
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
        if(ObjectUtil.isNull(this.wxId.getText()))
            record.setMaintainId(ObjectUtil.getWxId());
        else {
            record.setMaintainId(this.wxId.getText());
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
        CheckMaintainRecord record = new CheckMaintainRecord();
        if(ObjectUtil.isAllNull(tfMaintainCardNo.getText())){
            StageManager.nullWarn("维修卡编号不能为空");
            return;
        }
        if(this.queryStatus.getSelectionModel().getSelectedItem()==null) {
            StageManager.nullWarn("状态不能为空");
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
        if(ObjectUtil.isNull(this.wxId.getText()))
            record.setMaintainId(ObjectUtil.getWxId());
        else {
            record.setMaintainId(this.wxId.getText());
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
        PrintUtil.print(record.getMaintainId()+" "+record.getWxStatus());
    }

    public void setBaseData(Map<String,Object> map){
        this.tfId.setText(ObjectUtil.getString(map.get("id")));
        this.queryBrand.setValue(ObjectUtil.getString(map.get("maintain_card_brand")));
        this.wxId.setText(ObjectUtil.getString(map.get("maintain_id")));
        this.queryModel.setValue(ObjectUtil.getString(map.get("maintain_card_model")));
        this.tfMaintainCardNo.setText(ObjectUtil.getString(map.get("maintain_card_no")));
        this.queryCategory.setValue(ObjectUtil.getString(map.get("maintain_card_category")));
        this.queryShow.setValue(ObjectUtil.getString(map.get("wx_show")));
        this.queryStatus.setValue(ObjectUtil.getString(map.get("wx_status")));
        this.queryMethod.setValue(ObjectUtil.getString(map.get("wx_method")));
        this.queryResult.setValue(ObjectUtil.getString(map.get("wx_result")));
        if(ObjectUtil.isNotNull(ObjectUtil.getString(map.get("receive_date"))))
            this.dpReceiveDate.setValue(LocalDate.parse(ObjectUtil.getString(map.get("receive_date")).substring(0,10), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        if(ObjectUtil.isNotNull(ObjectUtil.getString(map.get("complete_date"))))
            this.dpCompleteDate.setValue(LocalDate.parse(ObjectUtil.getString(map.get("complete_date")).substring(0,10), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        this.queryUser.setValue(ObjectUtil.getString(map.get("maintain_user")));
        this.taMaintainDesc.setText(ObjectUtil.getString(map.get("maintain_desc")));
        this.taMaintainFitting.setText(ObjectUtil.getString(map.get("maintain_fitting")));
    }

    public void createMaintainId(){
        this.wxId.setText(ObjectUtil.getWxId());
    }

    public void chooseOpen(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.CHECK_FITTING_CHOOSE.fxml()));
        Parent root = loader.load();
        Scene scene = new Scene(root, 450, 400); // 页面大小
        // StageManager.initStyle(dialog);
        Stage dialog = new Stage();
        dialog.setTitle(FxmlView.CHECK_FITTING_CHOOSE.title()); // 页面标题
        dialog.setScene(scene);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initOwner(Main.mainStage);
        dialog.centerOnScreen();
        CheckFittingChooseController controller = loader.getController();
        controller.setDialog(dialog);
        controller.setResultHandle(a->{
            Map<String,Object> map = (Map<String,Object>)a;
            String desc = String.format("编号:%s,型号:%s,名称:%s,厂商:%s",
                    ObjectUtil.getString(map.get("fitting_no")),ObjectUtil.getString(map.get("fitting_model")),
                    ObjectUtil.getString(map.get("fitting_name")),ObjectUtil.getString(map.get("factory")));
            taMaintainFitting.setText(ObjectUtil.isNull(taMaintainFitting.getText())?
                    desc:(taMaintainFitting.getText()+"\n"+desc));
            return a;
        });
        dialog.show();
    }
}
