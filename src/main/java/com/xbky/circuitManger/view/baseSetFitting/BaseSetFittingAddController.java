package com.xbky.circuitManger.view.baseSetFitting;

import com.xbky.circuitManger.dao.FittingIntoInfoDao;
import com.xbky.circuitManger.entity.FittingIntoInfo;
import com.xbky.circuitManger.entity.ProductType;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BaseSetFittingAddController {

    @FXML
    public TextField tfId;
    @FXML
    public TextField tfNo;
    @FXML
    public TextField tfName;
    @FXML
    public TextField tfModel;
    @FXML
    public TextField tfUnit;
    @FXML
    public TextField tfFactory;


    FittingIntoInfoDao dao = new FittingIntoInfoDao();
    private  static Stage dialog = null;

    private  static Runnable resultHandle = null;


    public void cancelSubmit(ActionEvent actionEvent) {
        if(dialog!=null) {
            dialog.close();
        }
    }

    public void submitData(ActionEvent actionEvent) {
        FittingIntoInfo fii = new  FittingIntoInfo();
        if(ObjectUtil.hasNull(tfNo.getText(),tfName.getText(),tfModel.getText(),tfFactory.getText(),tfUnit.getText())){
            StageManager.nullWarn();
            return;
        };
        fii.setFittingNo(tfNo.getText());
        fii.setFittingName(tfName.getText());
        fii.setFittingModel(tfModel.getText());
        fii.setFactory(tfFactory.getText());
        fii.setUnit(tfUnit.getText());
        if(dao.isExistSome(fii)){
            StageManager.nullWarn("已存在相同的数据！");
            return;
        }
        if(ObjectUtil.isNull(tfId.getText())) {
            dao.add(fii);
        }else{
            fii.setId(ObjectUtil.getLong(tfId.getText()));
            dao.modify(fii);
        }
        if(dialog!=null) {
            dialog.close();
        }
        if (resultHandle != null) {
            resultHandle.run();
        }
    }

    public static Stage getDialog() {
        return dialog;
    }

    public static void setDialog(Stage dialog) {
        BaseSetFittingAddController.dialog = dialog;
    }

    public static Runnable getResultHandle() {
        return resultHandle;
    }

    public static void setResultHandle(Runnable resultHandle) {
        BaseSetFittingAddController.resultHandle = resultHandle;
    }

    public void setBaseData(FittingIntoInfo fii) {
        this.tfId.setText(fii.getId()+"");
        this.tfNo.setText(fii.getFittingNo());
        this.tfName.setText(fii.getFittingName());
        this.tfModel.setText(fii.getFittingModel());
        this.tfFactory.setText(fii.getFactory());
        this.tfUnit.setText(fii.getUnit());
    }
}
