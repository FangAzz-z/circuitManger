package com.xbky.circuitManger.view.system;

import com.xbky.circuitManger.AppIntializer;
import com.xbky.circuitManger.utils.DBUtil;
import com.xbky.circuitManger.utils.DataDBUtil;
import com.xbky.circuitManger.utils.DialogUtil;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SystemDbController implements Initializable {

    @FXML
    public TextField tfDbPathChange;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String dbPath = DBUtil.getDbPath();
        tfDbPathChange.setText(dbPath);
    }

    public void dbPathChange(ActionEvent actionEvent) {
        File file = DialogUtil.showExportDbFileDialog("请选择数据源路径");
        tfDbPathChange.setText(file.getPath());
    }

    public void submit(ActionEvent actionEvent) {
        String path = ObjectUtil.isNull(tfDbPathChange.getText())?"":tfDbPathChange.getText();
        int res = DBUtil.updatePath(path);
        if (res == 1) {
            StageManager.nullWarn("数据源设置成功！");
            DataDBUtil.resetPath();
            AppIntializer.setDb();
        }
    }
}
