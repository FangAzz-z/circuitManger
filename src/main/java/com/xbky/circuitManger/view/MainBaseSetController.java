package com.xbky.circuitManger.view;

import com.xbky.circuitManger.view.common.FxmlView;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainBaseSetController implements Initializable {

    private static Logger log = LoggerFactory.getLogger(MainController.class);

    @FXML
    private StackPane root;

    @FXML
    private HBox main;

    @FXML
    private VBox drawer;

    @FXML
    private ScrollPane scroll;

    @FXML
    private VBox views;

    @FXML
    private Button baseSetPt;

    @FXML
    private Button baseSetInfo;

    @FXML
    private Button baseSetUser;

    @FXML
    private Button baseSetFitting;

    @FXML
    private VBox content;

    @FXML
    private Button hamburger;

    @FXML
    private Label title;

    @FXML
    private ScrollPane body;

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
    public void setFitting(MouseEvent mouseEvent) {
        updateBody(FxmlView.BASESET_FITTING);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateBody(FxmlView.BASESET_PT);
    }

    private void updateBody(FxmlView view) {
        title.setText(view.title());
        Parent viewRootNodeHierarchy = StageManager.loadViewNodeHierarchy(view.fxml());
        body.setContent(viewRootNodeHierarchy);
    }
}
