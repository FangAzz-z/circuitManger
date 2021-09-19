package com.xbky.circuitManger.view.check;

import com.xbky.circuitManger.dao.CheckFittingRecordDao;
import com.xbky.circuitManger.entity.CheckFittingRecord;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class CheckFittingAddController implements Initializable {

    @FXML
    public TextField tfId;
    @FXML
    public TextField cbModel;
    @FXML
    public TextField cbName;
    @FXML
    public TextField cbNo;
    @FXML
    public TextField tfNum;
    @FXML
    public TextField tfLowLimit;


    CheckFittingRecordDao dao = new CheckFittingRecordDao();

    private  static Stage dialog = null;
    private  static Runnable resultHandle = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public  Stage getDialog() {
        return dialog;
    }

    public  void setDialog(Stage dialog) {
        this.dialog = dialog;
    }

    public  Runnable getResultHandle() {
        return resultHandle;
    }

    public  void setResultHandle(Runnable resultHandle) {
        this.resultHandle = resultHandle;
    }

    public void cancelSubmit(ActionEvent actionEvent) {
        if(dialog!=null) {
            dialog.close();
        }
    }

    public void submitData(ActionEvent actionEvent) {
        CheckFittingRecord record = new CheckFittingRecord();

        if(ObjectUtil.isNull(this.tfNum.getText())){
            StageManager.nullWarn("配件数量不能为空");
            return;
        }else if(ObjectUtil.isNotInteger(this.tfNum.getText())){
            StageManager.nullWarn("配件数量必须传入数字");
            return;
        }
        if(ObjectUtil.isNull(this.tfLowLimit.getText())){
            StageManager.nullWarn("低限预警不能为空");
            return;
        }else if(ObjectUtil.isNotInteger(this.tfLowLimit.getText())){
            StageManager.nullWarn("低限预警必须传入数字");
            return;
        }
        if(ObjectUtil.isNull(this.cbModel.getText())) {
            StageManager.nullWarn("配件型号不能为空");
        }
        if(ObjectUtil.isNull(this.cbName.getText())){
            StageManager.nullWarn("配件名称不能为空");
            return;
        }
        if (ObjectUtil.isNull(this.cbNo.getText())) {
            StageManager.nullWarn("配件编号不能为空");
            return;
        }
        record.setFittingModel(this.cbModel.getText());
        record.setFittingName(this.cbName.getText());
        record.setFittingNo(this.cbNo.getText());
        record.setFittingNum(ObjectUtil.getInt(this.tfNum.getText()));
        record.setLowLimit(ObjectUtil.getInt(this.tfLowLimit.getText()));
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

    public void setBaseData(Map<String,Object> map){
        this.tfId.setText(ObjectUtil.getString(map.get("id")));
        this.cbNo.setText(ObjectUtil.getString(map.get("fitting_no")));
        this.cbModel.setText(ObjectUtil.getString(map.get("fitting_model")));
        this.cbName.setText(ObjectUtil.getString(map.get("fitting_name")));
        this.tfNum.setText(ObjectUtil.getString(map.get("fitting_num")));
        this.tfLowLimit.setText(ObjectUtil.getString(map.get("low_limit")));
    }
}
