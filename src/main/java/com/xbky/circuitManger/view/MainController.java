package com.xbky.circuitManger.view;

import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.dao.SystemUserDao;
import com.xbky.circuitManger.dao.SystemUserRoleDao;
import com.xbky.circuitManger.service.ProgramService;
import com.xbky.circuitManger.utils.ImageUtil;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.utils.PrintUtil;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;


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

    @FXML
    public VBox wxCheck;
    @FXML
    public VBox baseChange;
    @FXML
    public VBox bkTest;
    @FXML
    public VBox printer;
    @FXML
    public VBox txConnect;
    @FXML
    public VBox cxDown;
    @FXML
    public VBox wxManager;
    @FXML
    public VBox systemSet;

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
    ProgramService service = new ProgramService();
    SystemUserRoleDao loginDao = new SystemUserRoleDao();

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

        if(!"admin".equals(LoginController.loginName)) {
            systemSet.setVisible(false);
            Map<String, VBox> moduleMap = new HashMap<>();
            moduleMap.put("维修管理", wxManager);
            moduleMap.put("程序下载", cxDown);
            moduleMap.put("通讯连接", txConnect);
            moduleMap.put("打印终端连接", printer);
            moduleMap.put("维修板卡测试", bkTest);
            moduleMap.put("基础设置", baseChange);
            moduleMap.put("维修登记", wxCheck);


            List<Map<String, Object>> roleList = loginDao.getDataByUser(LoginController.loginName);
            if (ObjectUtil.isNotEmpty(roleList)) {
                moduleMap.entrySet().forEach(a->a.getValue().setVisible(false));
                roleList.forEach(a->{
                    moduleMap.get(ObjectUtil.getString(a.get("module_name"))).setVisible(true);
                });
            }
        }

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
        try {
            service.programRun("程序下载");
        } catch (Exception e) {
            StageManager.nullWarn(e.getMessage());
        }
    }

    public void txConnect(ActionEvent actionEvent) {
        try {
            service.programRun("通讯连接");
        } catch (Exception e) {
            StageManager.nullWarn(e.getMessage());
        }
    }

    public void printerZd(ActionEvent actionEvent) {
        try {
            PrintUtil.print(ObjectUtil.getWxId()+" 维修完成");
          // ImageUtil.printToLabel(ObjectUtil.getWxId()+" 维修完成");
           // service.programRun("打印终端连接");
        } catch (Exception e) {
            StageManager.nullWarn(e.getMessage());
        }
    }

    public void bkTest(ActionEvent actionEvent) {
        try {
            service.programRun("维修板卡测试");
        } catch (Exception e) {
            StageManager.nullWarn(e.getMessage());
        }
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

    public void setModule(){
        if(!"admin".equals(LoginController.loginName)) {
            Map<String, VBox> moduleMap = new HashMap<>();
            moduleMap.put("维修管理", wxManager);
            moduleMap.put("程序下载", cxDown);
            moduleMap.put("通讯连接", txConnect);
            moduleMap.put("打印终端连接", printer);
            moduleMap.put("维修板卡测试", bkTest);
            moduleMap.put("基础设置", baseChange);
            moduleMap.put("维修登记", wxCheck);


            List<Map<String, Object>> roleList = loginDao.getDataByModule(LoginController.loginName);
            if (ObjectUtil.isNotEmpty(roleList)) {
                moduleMap.entrySet().forEach(a->a.getValue().setVisible(false));
                roleList.forEach(a->{
                    moduleMap.get(ObjectUtil.getString(a.get("module_name"))).setVisible(true);
                });
            }
        }
    }

}
