package com.xbky.circuitManger.view.check;

import com.xbky.circuitManger.view.common.Function;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CheckFittingClearController implements Initializable {

    @FXML
    public ComboBox cbItem;

    private  static Stage dialog = null;
    private  static Function resultHandle = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setBaseData(List<String> items){
        cbItem.setItems(FXCollections.observableArrayList(items));
    }

    public void clearSubmit(ActionEvent actionEvent) {
        if(this.cbItem.getSelectionModel().getSelectedItem()!=null) {
            resultHandle.apply(this.cbItem.getSelectionModel().getSelectedItem().toString());
            if (dialog != null) {
                dialog.close();
            }
        }else
        {
            StageManager.nullWarn("请选中要删除条目");
        }
    }

    public  Stage getDialog() {
        return dialog;
    }

    public  void setDialog(Stage dialog) {
        CheckFittingClearController.dialog = dialog;
    }

    public  Function getResultHandle() {
        return resultHandle;
    }

    public  void setResultHandle(Function resultHandle) {
        CheckFittingClearController.resultHandle = resultHandle;
    }
}
