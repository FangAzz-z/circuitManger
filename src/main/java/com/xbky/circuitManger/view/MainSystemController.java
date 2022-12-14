package com.xbky.circuitManger.view;

import com.xbky.circuitManger.view.common.FxmlView;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainSystemController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateBody(FxmlView.PRODUCE_PRO);
    }

    @FXML
    private Label title;

    @FXML
    private ScrollPane body;

    /**
     *  系统设置->数据备份
     */
    public void dataBackUp(ActionEvent actionEvent) {
        updateBody(FxmlView.SYSTEM_DATA);
    }

    /**
     *  系统设置->使用方信息变更
     */
    public void infoChange(ActionEvent actionEvent) {
        updateBody(FxmlView.SYSTEM_INFO_CHANGE);
    }

    /**
     *  系统设置->打印机设置
     */
    public void printer(ActionEvent actionEvent) {
        updateBody(FxmlView.SYSTEM_PRINTER);
    }

    /**
     *  系统设置->程序配置
     */
    public void program(ActionEvent actionEvent) {
        updateBody(FxmlView.PRODUCE_PRO);
    }

    /**
     *  系统设置->数据源设置
     */
    public void systemDb(ActionEvent actionEvent) {
        updateBody(FxmlView.SYSTEM_DB);
    }

    @FXML
    void roleManger(ActionEvent event) {
        updateBody(FxmlView.SYSTEM_USER_ROLE);
    }

    @FXML
    void systemUserManger(ActionEvent event) {
        updateBody(FxmlView.SYSTEM_USER);
    }

    private void updateBody(FxmlView view) {
        title.setText(view.title());
        Parent viewRootNodeHierarchy = StageManager.loadViewNodeHierarchy(view.fxml());
        body.setContent(viewRootNodeHierarchy);
    }

}
