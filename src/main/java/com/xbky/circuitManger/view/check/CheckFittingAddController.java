package com.xbky.circuitManger.view.check;

import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.dao.CheckFittingRecordDao;
import com.xbky.circuitManger.entity.CheckFittingRecord;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.view.common.Function;
import com.xbky.circuitManger.view.common.FxmlView;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
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
    @FXML
    public Button chooseOpen;


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
            StageManager.nullWarn("????????????????????????");
            return;
        }else if(ObjectUtil.isNotInteger(this.tfNum.getText())){
            StageManager.nullWarn("??????????????????????????????");
            return;
        }
        if(ObjectUtil.isNull(this.tfLowLimit.getText())){
            StageManager.nullWarn("????????????????????????");
            return;
        }else if(ObjectUtil.isNotInteger(this.tfLowLimit.getText())){
            StageManager.nullWarn("??????????????????????????????");
            return;
        }
        if(ObjectUtil.isNull(this.cbModel.getText())) {
            StageManager.nullWarn("????????????????????????");
        }
        if(ObjectUtil.isNull(this.cbName.getText())){
            StageManager.nullWarn("????????????????????????");
            return;
        }
        if (ObjectUtil.isNull(this.cbNo.getText())) {
            StageManager.nullWarn("????????????????????????");
            return;
        }
        record.setFittingModel(this.cbModel.getText());
        record.setFittingName(this.cbName.getText());
        record.setFittingNo(this.cbNo.getText());
        record.setFittingNum(ObjectUtil.getInt(this.tfNum.getText()));
        record.setLowLimit(ObjectUtil.getInt(this.tfLowLimit.getText()));
        if(ObjectUtil.isNull(tfId.getText())) {
            if(dao.getRecordByNo(record.getFittingNo())!=null){
                StageManager.nullWarn(String.format("?????????%s???????????????!",record.getFittingNo()));
                return;
            }
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
        this.cbNo.setEditable(false);
        this.cbModel.setText(ObjectUtil.getString(map.get("fitting_model")));
        this.cbModel.setEditable(false);
        this.cbName.setText(ObjectUtil.getString(map.get("fitting_name")));
        this.cbName.setEditable(false);
        this.tfNum.setText(ObjectUtil.getString(map.get("fitting_num")));
        this.tfLowLimit.setText(ObjectUtil.getString(map.get("low_limit")));
        chooseOpen.setVisible(false);
    }
    public void chooseOpen(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.CHECK_FITTING_CHOOSE.fxml()));
        Parent root = loader.load();
        Scene scene = new Scene(root, 450, 400); // ????????????
        // StageManager.initStyle(dialog);
        Stage dialog = new Stage();
        dialog.setTitle(FxmlView.CHECK_FITTING_CHOOSE.title()); // ????????????
        dialog.setScene(scene);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initOwner(Main.mainStage);
        dialog.centerOnScreen();
        CheckFittingChooseController controller = loader.getController();
        controller.setDialog(dialog);
        controller.setResultHandle(new Function() {
               @Override
               public Object apply(Object a) {
                   Map<String, Object> map = (Map<String, Object>) a;
                   cbNo.setText(ObjectUtil.getString(map.get("fitting_no")));
                   cbName.setText(ObjectUtil.getString(map.get("fitting_name")));
                   cbModel.setText(ObjectUtil.getString(map.get("fitting_model")));
                   tfNum.setText(ObjectUtil.getString(map.get("num")));
                   return a;
               }
           }
        );
        dialog.show();
    }

}
