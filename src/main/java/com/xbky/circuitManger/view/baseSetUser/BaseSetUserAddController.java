package com.xbky.circuitManger.view.baseSetUser;

import com.xbky.circuitManger.dao.MaintainUserDao;
import com.xbky.circuitManger.entity.MaintainUser;
import com.xbky.circuitManger.entity.ProductType;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.slf4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

import static org.slf4j.LoggerFactory.getLogger;

public class BaseSetUserAddController  implements Initializable {
    private static final Logger log = getLogger(BaseSetUserAddController.class);

    @FXML
    public TextField tfId;
    @FXML
    public TextField tfName;
    @FXML
    public ToggleGroup gender;
    @FXML
    public TextField tfDepartment;
    @FXML
    public TextField tfJob;
    @FXML
    public TextField tfPhone;
    @FXML
    public RadioButton rbMale;
    @FXML
    public RadioButton rbFemale;


    MaintainUserDao dao = new MaintainUserDao();
    private  static Stage dialog = null;
    private  static Runnable resultHandle = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void cancelSubmit(ActionEvent actionEvent) {
        if(dialog!=null) {
            dialog.close();
        }
    }

    public void submitData(ActionEvent actionEvent) {
        MaintainUser mu = new  MaintainUser();
        if(ObjectUtil.hasNull(tfName.getText(),tfDepartment.getText(),tfJob.getText(),tfPhone.getText())){
            StageManager.nullWarn();
            return;
        };
        mu.setPhone(tfPhone.getText());
        mu.setName(tfName.getText());
        mu.setSex(String.valueOf(((RadioButton)this.gender.getSelectedToggle()).getAccessibleText()));
        mu.setDepartment(tfDepartment.getText());
        mu.setJob(tfJob.getText());
        mu.setPhone(tfPhone.getText());
        if(ObjectUtil.isNull(tfId.getText())) {
            dao.add(mu);
        }else{
            mu.setId(ObjectUtil.getLong(tfId.getText()));
            dao.modify(mu);
        }
        if(dialog!=null) {
            dialog.close();
        }
        if (resultHandle != null) {
            resultHandle.run();
        }

    }

    public void setBaseData(MaintainUser mu){
        this.tfId.setText(mu.getId()+"");
        this.tfName.setText(mu.getName());
        this.tfDepartment.setText(mu.getDepartment());
        this.tfJob.setText(mu.getJob());
        this.tfPhone.setText(mu.getPhone());
        if ("女".equals(mu.getSex())){
            this.rbFemale.setSelected(true);
        }else {
            this.rbMale.setSelected(true);
        }
    }


    public static Stage getDialog() {
        return dialog;
    }

    public static void setDialog(Stage dialog) {
        BaseSetUserAddController.dialog = dialog;
    }

    public static Runnable getResultHandle() {
        return resultHandle;
    }

    public static void setResultHandle(Runnable resultHandle) {
        BaseSetUserAddController.resultHandle = resultHandle;
    }
}
