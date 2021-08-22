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
    public void setPt(MouseEvent mouseEvent) {

        updateBody(FxmlView.BASESET_PT);
    }
    /**
     *  基本设置->基础信息
     */
    public void setInfo(MouseEvent mouseEvent) {
        updateBody(FxmlView.BASESET_INFO);
    }
    /**
     *  基本设置->人员管理
     */
    public void setUser(MouseEvent mouseEvent) {
        updateBody(FxmlView.BASESET_USER);
    }
    /**
     *  基本设置->配件入库信息
     */
    public void setFtting(MouseEvent mouseEvent) {
        updateBody(FxmlView.BASESET_FITTING);
    }

    public void jfxTextField(MouseEvent mouseEvent) {
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


    public void queryManager(MouseEvent mouseEvent) {
    }
}
