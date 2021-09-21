package com.xbky.circuitManger.view.system;

import com.xbky.circuitManger.dao.ScreenDao;
import com.xbky.circuitManger.service.ScreenService;
import com.xbky.circuitManger.utils.DialogUtil;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SystemInfoChangeController implements Initializable {

    @FXML
    public RadioButton unBjSet;
    @FXML
    public RadioButton bjSet;
    @FXML
    public TextField tfImageUrl;
    @FXML
    public ColorPicker colorChange;
    @FXML
    public ToggleGroup bj;


    ScreenDao dao = new ScreenDao();
    ScreenService service = new ScreenService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int isSet = service.geIsSet();
        String imageUrl = service.getImageUrl();
        String fontColor = service.getFontColor();
        if (ObjectUtil.isNotNull(imageUrl)) {
            this.tfImageUrl.setText(imageUrl);
        }
        if (ObjectUtil.isNotNull(fontColor)) {
            this.colorChange.setValue(Color.web(fontColor,0.5));
        }
        if(isSet == 1){
            bjSet.setSelected(true);
        }else{
            unBjSet.setSelected(true);
        }
    }


    public void reset(ActionEvent actionEvent) {
        unBjSet.setSelected(true);
        tfImageUrl.setText("");
        colorChange.setValue(Color.WHITE);
    }

    public void submit(ActionEvent actionEvent) {
        String radioValue = ((RadioButton)this.bj.getSelectedToggle()).getAccessibleText();
        int res = dao.modify(ObjectUtil.getInt(radioValue),tfImageUrl.getText(),colorChange.getValue().toString());
        if (res == 1) {
            StageManager.nullWarn("设置成功！需重启应用生效");
        }
    }

    public void imageChange(ActionEvent actionEvent) {
        File picFile = DialogUtil.showExportPicFileDialog("请选择图片路径");
        if (picFile != null) {
            tfImageUrl.setText(picFile.getPath());
        }
    }

}
