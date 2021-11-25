package com.xbky.circuitManger.view.system;

import com.xbky.circuitManger.common.enums.ModuleEnum;
import com.xbky.circuitManger.dao.SystemUserRoleDao;
import com.xbky.circuitManger.entity.FittingIntoInfo;
import com.xbky.circuitManger.entity.SystemUserRole;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class SystemUserRoleAddController  implements Initializable {

    @FXML
    public TextField tfId;
    @FXML
    public ComboBox userName;
    @FXML
    public ComboBox moduleName;

    private  static Stage dialog = null;
    private  static Runnable resultHandle = null;
    SystemUserRoleDao dao = new SystemUserRoleDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<String> user = new ArrayList<String>();
        List<Map<String,Object>> userList = dao.commonQueryAll("CM_SYSTEM_USER");
        for (int i = 0; i < userList.size(); i++) {
            Map<String, Object> a = userList.get(i);
            String userStr = (String)a.get("user_name");
            if(!"admin".equals(userStr)){
                user.add(userStr);
            }
        }
        List<String> module = new ArrayList<String>();
        for (int i = 0; i < ModuleEnum.values().length; i++) {
            ModuleEnum enums = ModuleEnum.values()[i];
            module.add(enums.getName());
        }
        userName.setItems(FXCollections.observableArrayList(user));
        moduleName.setItems(FXCollections.observableArrayList(module));
    }

    public void cancelSubmit(ActionEvent actionEvent) {
        if(dialog!=null) {
            dialog.close();
        }
    }

    public void submitData(ActionEvent actionEvent) {
        if(ObjectUtil.isAllNull(this.userName.getSelectionModel().getSelectedItem())){
            StageManager.nullWarn("账户名称不能为空");
            return;
        }

        if(ObjectUtil.isAllNull(this.moduleName.getSelectionModel().getSelectedItem())){
            StageManager.nullWarn("模块名称不能为空");
            return;
        }
        SystemUserRole sur = new SystemUserRole();
        sur.setUserName(ObjectUtil.getString(this.userName.getSelectionModel().getSelectedItem()));
        sur.setModuleName(ObjectUtil.getString(this.moduleName.getSelectionModel().getSelectedItem()));
        dao.add(sur);

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
        SystemUserRoleAddController.dialog = dialog;
    }

    public static Runnable getResultHandle() {
        return resultHandle;
    }

    public static void setResultHandle(Runnable resultHandle) {
        SystemUserRoleAddController.resultHandle = resultHandle;
    }
}
