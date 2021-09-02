package com.xbky.circuitManger.view;

import com.xbky.circuitManger.view.common.FxmlView;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainCheckController implements Initializable {
    @FXML
    private Label title;

    @FXML
    private ScrollPane body;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateBody(FxmlView.CHECK_MAINTAIN);
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

    private void updateBody(FxmlView view) {
        title.setText(view.title());
        Parent viewRootNodeHierarchy = StageManager.loadViewNodeHierarchy(view.fxml());
        body.setContent(viewRootNodeHierarchy);
    }
}
