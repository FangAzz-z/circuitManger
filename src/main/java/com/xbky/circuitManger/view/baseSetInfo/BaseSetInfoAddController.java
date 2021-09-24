package com.xbky.circuitManger.view.baseSetInfo;

import com.xbky.circuitManger.dao.BaseInfoDao;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class BaseSetInfoAddController implements Initializable {


    private  static Stage dialog = null;

    private  static Runnable resultHandld = null;

    BaseInfoDao baseInfoDao = new BaseInfoDao();

    @FXML
    public Label dialog_label;
    @FXML
    public TextField dialog_content;
    @FXML
    public TextField tfId;
    @FXML
    public Label dialog_label_code;
    @FXML
    public TextField dialog_code;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    //维修产品状态 相关方法
    public void cancelSubmit(ActionEvent actionEvent) {
        if(dialog!=null) {
            dialog.close();
        }
    }

    public void submitData(ActionEvent actionEvent) {
        if(ObjectUtil.hasNull(this.dialog_content.getText())){
            StageManager.nullWarn("提示","输入值不能为空");
            return;
        }
        String table = "";
        if("维修产品状态".equals(this.dialog_label.getText())){
            table = "CM_BASE_PT_STATUS";
        }else if("故障现象".equals(this.dialog_label.getText())){
            table = "CM_BASE_FAULT_SHOW";
        }else if("维修措施".equals(this.dialog_label.getText())){
            table = "CM_BASE_MAINTAIN_METHOD";
        }else{
            table = "CM_BASE_HANDLE_RESULT";
        }
        if(this.baseInfoDao.isExitSome(table,this.dialog_content.getText(),null)){
            StageManager.nullWarn("已存在相同的数据！");
            return;
        }
        if (ObjectUtil.isNotNull(this.tfId.getText())) {
            this.baseInfoDao.updateById(table, this.dialog_content.getText(),this.tfId.getText());
        }else{
            this.baseInfoDao.add(table, this.dialog_content.getText());
        }
        if(dialog!=null) {
            dialog.close();
        }
        if (resultHandld != null) {
            resultHandld.run();
        }
    }
    //
    public void setBaseData(String id,String label,String content){
        this.tfId.setText(id);
        this.dialog_label.setText(label);
        this.dialog_content.setText(content);
    }

    public void setBaseDataV2(String id,String content,String code){
        this.tfId.setText(id);
        this.dialog_code.setText(code);
        this.dialog_content.setText(content);
    }


    public static Stage getDialog() {
        return dialog;
    }

    public static void setDialog(Stage dialog) {
        BaseSetInfoAddController.dialog = dialog;
    }


    public static Runnable getResultHandld() {
        return resultHandld;
    }

    public static void setResultHandld(Runnable resultHandld) {
        BaseSetInfoAddController.resultHandld = resultHandld;
    }

    public void submitDataV2(ActionEvent actionEvent) {
        if(ObjectUtil.hasNull(this.dialog_content.getText(),this.dialog_code.getText())){
            StageManager.nullWarn("提示","输入值不能为空");
            return;
        }
        if(this.baseInfoDao.isExitSome("CM_BASE_FAULT_SHOW",this.dialog_content.getText(),this.dialog_code.getText())){
            StageManager.nullWarn("已存在相同的数据！");
            return;
        }
        if (ObjectUtil.isNotNull(this.tfId.getText())) {
            this.baseInfoDao.updateByIdV2("CM_BASE_FAULT_SHOW", this.dialog_content.getText(),this.dialog_code.getText(),this.tfId.getText());
        }else{
            this.baseInfoDao.addV2("CM_BASE_FAULT_SHOW", this.dialog_content.getText(),this.dialog_code.getText());
        }
        if(dialog!=null) {
            dialog.close();
        }
        if (resultHandld != null) {
            resultHandld.run();
        }
    }

}
