package com.xbky.circuitManger.view.check;

import com.xbky.circuitManger.dao.CheckFittingRecordDao;
import com.xbky.circuitManger.entity.CheckFittingRecord;
import com.xbky.circuitManger.utils.ObjectUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckFittingAddController implements Initializable {

    @FXML
    public TextField tfId;
    @FXML
    public ComboBox cbModel;
    @FXML
    public ComboBox cbName;
    @FXML
    public ComboBox cbNo;
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
}
