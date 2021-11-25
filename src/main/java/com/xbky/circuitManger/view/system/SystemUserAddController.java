package com.xbky.circuitManger.view.system;

import com.xbky.circuitManger.dao.SystemUserDao;
import com.xbky.circuitManger.entity.FittingIntoInfo;
import com.xbky.circuitManger.entity.SystemUser;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SystemUserAddController implements Initializable {

    @FXML
    public TextField tfId;
    @FXML
    public TextField tfName;
    @FXML
    public TextField tfPassword;

    private  static Stage dialog = null;

    private  static Runnable resultHandle = null;


    SystemUserDao dao = new SystemUserDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void cancelSubmit(ActionEvent actionEvent) {
        if(dialog!=null) {
            dialog.close();
        }
    }

    public void submitData(ActionEvent actionEvent) {
        if(ObjectUtil.hasNull(tfName.getText(),tfPassword.getText())){
            StageManager.nullWarn();
            return;
        }
        if(ObjectUtil.isNull(tfId.getText())) {
            if("admin".equals(tfName.getText())){
                StageManager.nullWarn("不能添加名称为admin的用户");
                return;
            }
            SystemUser su = new SystemUser();
            su.setUserName(tfName.getText());
            su.setUserPassword(tfPassword.getText());
            dao.add(su);
        }else{
            dao.modify(tfId.getText(), tfPassword.getText());
        }
        if(dialog!=null) {
            dialog.close();
        }
        if (resultHandle != null) {
            resultHandle.run();
        }
    }

    public void setBaseData(String id,String name,String password){
        this.tfId.setText(id);
        this.tfName.setText(name);
        this.tfPassword.setText(password);

        this.tfName.setEditable(false);
    }

    public static Stage getDialog() {
        return dialog;
    }

    public static void setDialog(Stage dialog) {
        SystemUserAddController.dialog = dialog;
    }

    public static Runnable getResultHandle() {
        return resultHandle;
    }

    public static void setResultHandle(Runnable resultHandle) {
        SystemUserAddController.resultHandle = resultHandle;
    }

}
