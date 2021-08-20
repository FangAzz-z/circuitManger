package com.xbky.circuitManger.view;

import com.xbky.circuitManger.view.common.FxmlView;
import com.xbky.circuitManger.view.common.StageManagerSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    @FXML
    public ScrollPane body;

    @FXML
    public Label title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     *  基本设置->维修产品类型
     */
    public void baseSetPt(MouseEvent mouseEvent) {
        updateBody(FxmlView.BASESET_PT);
    }
    /**
     *  基本设置->基础信息
     */
    public void baseSetInfo(MouseEvent mouseEvent) {
        updateBody(FxmlView.BASESET_INFO);
    }

    public void jfxTextField(MouseEvent mouseEvent) {
    }

    public void queryManager(MouseEvent mouseEvent) {

    }

    public void webview(MouseEvent mouseEvent) {
    }

    public void webview2(MouseEvent mouseEvent) {
    }

    public void altLayout(MouseEvent mouseEvent) {
    }
    public void baseSet(MouseEvent mouseEvent) {
    }


    private void updateBody(FxmlView view) {
        title.setText(view.title());
        StageManagerSingleton.getSingleton().switchContent(view, body);
    }


}
