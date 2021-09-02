package com.xbky.circuitManger.view;

import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.view.common.FxmlView;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class MainController implements Initializable {
    private static Logger log = LoggerFactory.getLogger(MainController.class);
    @FXML
    public ScrollPane body;

    @FXML
    public Label title;

    @FXML
    public GridPane secondPage;
    @FXML
    public GridPane firstPage;

    //主界面按钮
    @FXML
    private Button buWxManager;

    @FXML
    private Button buCxDown;

    @FXML
    private Button buTxConnect;

    @FXML
    private Button buPrinter;

    @FXML
    private Button buBkTest;

    @FXML
    private Button buAppExit;

    @FXML
    public Button buBaseChange;

    @FXML
    private Button buWxCheck;

    @FXML
    private Button buSystemSet;

    @FXML
    private Button buGoBack;

    private Stage secondStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Button> buttonList = Arrays.asList(buWxManager,buCxDown,buTxConnect,buPrinter,buBkTest,buAppExit,buBaseChange,buWxCheck,buSystemSet,buGoBack);
        buttonList.forEach(button->{
            //鼠标进入
            button.setOnMouseEntered(event->{
                button.setStyle("-fx-border-color: #e0e3e2;-fx-background-color: transparent;-fx-border-width:3;");
                ((ImageView)button.getGraphic()).setFitWidth(160);
                ((ImageView)button.getGraphic()).setFitHeight(160);
            });
            //鼠标退出
            button.setOnMouseExited(event->{
                button.setStyle("-fx-border-color: transparent;-fx-background-color: transparent");
                ((ImageView)button.getGraphic()).setFitWidth(150);
                ((ImageView)button.getGraphic()).setFitHeight(150);
            });
            button.setOnMousePressed(event -> {
                ((ImageView)button.getGraphic()).setFitWidth(140);
                ((ImageView)button.getGraphic()).setFitHeight(140);

            });
        });
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

/*    public void pressedTest(MouseEvent mouseEvent) {
        System.out.println("hello");
        imageViewTest.setImage(new Image("/icons/wx2.png"));
    }

    public void released(MouseEvent mouseEvent) {
        imageViewTest.setImage(new Image("/icons/wx.png"));

    }*/

    /**
     * 维修管理
     * @param actionEvent
     */
    public void wxManager(ActionEvent actionEvent) {
        this.firstPage.setVisible(false);
        this.secondPage.setVisible(true);
    }

    public void cxDown(ActionEvent actionEvent) {
        StageManager.nullWarn("暂无配置");
    }

    public void txConnect(ActionEvent actionEvent) {
        StageManager.nullWarn("暂无配置");
    }

    public void printerZd(ActionEvent actionEvent) {
        StageManager.nullWarn("暂无配置");
    }

    public void bkTest(ActionEvent actionEvent) {
        StageManager.nullWarn("暂无配置");
    }

    public void appExit(ActionEvent actionEvent) {
        Main.closeAllPopups();
    }

    public void wxCheck(ActionEvent actionEvent) {
        openModule(FxmlView.MAIN_CHECK);
    }

    public void systemSet(ActionEvent actionEvent) {
        openModule(FxmlView.MAIN_SYSTEM);
    }

    /**
     * 返回上一级
     * @param actionEvent
     */
    public void goBack(ActionEvent actionEvent) {
        this.firstPage.setVisible(true);
        this.secondPage.setVisible(false);
    }

    public void baseChange(ActionEvent actionEvent) {
        openModule(FxmlView.MAIN_BASE_SET);
    }

    private void openModule(FxmlView view) {
        if (secondStage != null) {
            secondStage.close();
        }
        secondStage = new Stage();
        secondStage.setTitle(view.title()); // 页面标题
        secondStage.getIcons().add(new Image("/icons/logo2.png")); // 页面logo
        secondStage .initStyle(StageStyle.UTILITY);
        secondStage.initOwner(Main.mainStage);
        Parent root = loadViewNodeHierarchy(view.fxml());
        Scene primarySence = new Scene(root, 1200, 640); // 页面大小
        secondStage.setScene(primarySence);
        secondStage.show();
    }

}
