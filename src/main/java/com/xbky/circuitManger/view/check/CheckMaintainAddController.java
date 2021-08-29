package com.xbky.circuitManger.view.check;

import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.view.baseSetUser.BaseSetUserAddController;
import com.xbky.circuitManger.view.common.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckMaintainAddController implements Initializable {


    private  static Stage dialog = null;
    private  static Runnable resultHandle = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public static Stage getDialog() {
        return dialog;
    }

    public static void setDialog(Stage dialog) {
        CheckMaintainAddController.dialog = dialog;
    }

    public static Runnable getResultHandle() {
        return resultHandle;
    }

    public static void setResultHandle(Runnable resultHandle) {
        CheckMaintainAddController.resultHandle = resultHandle;
    }

    public void cancelSubmit(ActionEvent actionEvent) {
    }

    public void submitData(ActionEvent actionEvent) {
    }
}
