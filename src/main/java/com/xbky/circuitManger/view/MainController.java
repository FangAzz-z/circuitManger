package com.xbky.circuitManger.view;

import com.xbky.circuitManger.view.common.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    private static Logger log = LoggerFactory.getLogger(MainController.class);
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

    /**
     *  维修登记管理->维修登记单
     */
    public void checkMaintain(ActionEvent actionEvent) {
        updateBody(FxmlView.CHECK_MAINTAIN);
    }
    /**
     *  维修登记管理->配件登记单
     */
    public void checkFitting(ActionEvent actionEvent) {
        updateBody(FxmlView.CHECK_FITTING);
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
        Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.fxml());
        body.setContent(viewRootNodeHierarchy);
    }

    private Parent loadViewNodeHierarchy(String fxmlFilePath) {
        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            Objects.requireNonNull(rootNode, "A Root FXML node must not be null");
        } catch (Exception exception) {
            log.info("", exception);
        }
        return rootNode;
    }


    public void queryManager(MouseEvent mouseEvent) {
    }

}
