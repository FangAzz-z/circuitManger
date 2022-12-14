package com.xbky.circuitManger.view;

import com.xbky.circuitManger.LoginManager;
import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.dao.SystemUserDao;
import com.xbky.circuitManger.dao.SystemUserRoleDao;
import com.xbky.circuitManger.service.ProgramService;
import com.xbky.circuitManger.service.ScreenService;
import com.xbky.circuitManger.utils.ImageUtil;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.utils.PrintUtil;
import com.xbky.circuitManger.view.common.FxmlView;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Statement;
import java.util.*;
import java.util.List;


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
    @FXML
    public BorderPane mainPane;

    @FXML
    public Label lbMaintain;
    @FXML
    public Label lbCxDown;
    @FXML
    public Label lbTxlj;
    @FXML
    public Label lbPrinter;
    @FXML
    public Label lbBkTest;
    @FXML
    public Label lbExit;
    @FXML
    public Label lbSystemSet;
    @FXML
    public Label lbWxCheck;
    @FXML
    public Label lbBaseSet;
    @FXML
    public Label lbTop;

    //???????????????
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
    ScreenService screenService = new ScreenService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Button> buttonList = Arrays.asList(buWxManager,buCxDown,buTxConnect,buPrinter,buBkTest,buAppExit,buBaseChange,buWxCheck,buSystemSet,buGoBack);
        for (int i = 0; i < buttonList.size(); i++) {
            final Button button = buttonList.get(i);
            //????????????
            button.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    button.setStyle("-fx-border-color: #e0e3e2;-fx-background-color: transparent;-fx-border-width:3;");
                    ((ImageView) button.getGraphic()).setFitWidth(160);
                    ((ImageView) button.getGraphic()).setFitHeight(160);
                }
            });
            //????????????
            button.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    button.setStyle("-fx-border-color: transparent;-fx-background-color: transparent");
                    ((ImageView) button.getGraphic()).setFitWidth(150);
                    ((ImageView) button.getGraphic()).setFitHeight(150);
                }
            });
            button.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ((ImageView) button.getGraphic()).setFitWidth(140);
                    ((ImageView) button.getGraphic()).setFitHeight(140);

                }
            });
        }

        if(!"admin".equals(LoginController.loginName)) {
            systemSet.setVisible(false);
            Map<String, VBox> moduleMap = new HashMap<String, VBox>();
            moduleMap.put("????????????", wxManager);
            moduleMap.put("????????????", cxDown);
            moduleMap.put("????????????", txConnect);
            moduleMap.put("??????????????????", printer);
            moduleMap.put("??????????????????", bkTest);
            moduleMap.put("????????????", baseChange);
            moduleMap.put("????????????", wxCheck);


            List<Map<String, Object>> roleList = loginDao.getDataByUser(LoginController.loginName);
            if (ObjectUtil.isNotEmpty(roleList)) {
                Iterator iterators = moduleMap.entrySet().iterator();
                while (iterators.hasNext()) {
                    Map.Entry<String, VBox> a = (Map.Entry<String, VBox>) iterators.next();
                    a.getValue().setVisible(false);
                }
                for (int i = 0; i < roleList.size(); i++) {
                    Map<String, Object> a = roleList.get(i);
                    moduleMap.get(ObjectUtil.getString(a.get("module_name"))).setVisible(true);
                }
            }
        }

        //??????????????????
        int isSet = screenService.geIsSet();
        if(isSet ==1) {
            //????????????
            String backgroundImage = screenService.getImageUrl();
            if (ObjectUtil.isNotNull(backgroundImage)) {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int width = (int) screenSize.getWidth();
                int height = (int) screenSize.getHeight();
                //String imageUrl = getClass().getResource("/icons/bj.jpeg").toExternalForm();
                String imageUrl = String.format("file:/%s", backgroundImage);
                BackgroundImage myBI = new BackgroundImage(new Image(imageUrl, width, height, false, true),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                mainPane.setBackground(new Background(myBI));
            }
            //????????????????????????
            String fontColor = screenService.getFontColor();
            if (ObjectUtil.isNotNull(fontColor)) {
                lbMaintain.setTextFill(Color.web(fontColor, 1));
                lbCxDown.setTextFill(Color.web(fontColor));
                lbTxlj.setTextFill(Color.web(fontColor));
                lbPrinter.setTextFill(Color.web(fontColor));
                lbBkTest.setTextFill(Color.web(fontColor));
                lbExit.setTextFill(Color.web(fontColor));
                lbSystemSet.setTextFill(Color.web(fontColor));
                lbWxCheck.setTextFill(Color.web(fontColor));
                lbBaseSet.setTextFill(Color.web(fontColor));
                lbTop.setTextFill(Color.web(fontColor));
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
     * ????????????
     * @param actionEvent
     */
    public void wxManager(ActionEvent actionEvent) {
        service.toBigScreen();
        this.firstPage.setVisible(false);
        this.secondPage.setVisible(true);
    }

    public void cxDown(ActionEvent actionEvent) {
        try {
            service.toBigScreen();
            service.programRun("????????????");
        } catch (Exception e) {
            StageManager.nullWarn(e.getMessage());
        }
    }

    public void txConnect(ActionEvent actionEvent) {
        try {
            service.toBigScreen();
            service.programRun("????????????");
        } catch (Exception e) {
            StageManager.nullWarn(e.getMessage());
        }
    }

    public void printerZd(ActionEvent actionEvent) {
        try {
           // PrintUtil.print(ObjectUtil.getWxId()+" ????????????");
          // ImageUtil.printToLabel(ObjectUtil.getWxId()+" ????????????");
            service.toBigScreen();
            service.programRun("??????????????????");
        } catch (Exception e) {
            StageManager.nullWarn(e.getMessage());
        }
    }

    public void bkTest(ActionEvent actionEvent) {
        try {
            service.toMiniScreen();
            service.programRun("??????????????????");
        } catch (Exception e) {
            StageManager.nullWarn(e.getMessage());
        }
    }

    public void appExit(ActionEvent actionEvent) {
        service.toBigScreen();
        Main.closeAllPopups(actionEvent);
    }

    public void wxCheck(ActionEvent actionEvent) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = new Double(screenSize.getWidth()*(1600/1920.0)).intValue();
        int height = new Double(screenSize.getHeight()*640/1080.0).intValue();
        openModule(FxmlView.MAIN_CHECK,width,height);
    }

    public void systemSet(ActionEvent actionEvent) {
        LoginManager.loadLogin();
       // openModule(FxmlView.MAIN_SYSTEM);
    }

    /**
     * ???????????????
     * @param actionEvent
     */
    public void goBack(ActionEvent actionEvent) {
        this.firstPage.setVisible(true);
        this.secondPage.setVisible(false);
    }

    public void baseChange(ActionEvent actionEvent) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = new Double(screenSize.getWidth()*(1250/1920.0)).intValue();
        int height = new Double(screenSize.getHeight()*640/1080.0).intValue();
        openModule(FxmlView.MAIN_BASE_SET,width,height);
    }
    private void openModule(FxmlView view){
        openModule(view,1200,640);
    }

    private void openModule(FxmlView view,int width,int height) {
        if (secondStage != null) {
            secondStage.close();
        }
        secondStage = new Stage();
        secondStage.setTitle(view.title()); // ????????????
        secondStage.getIcons().add(new Image("/icons/logo2.png")); // ??????logo
        //secondStage.initStyle(StageStyle.UTILITY);
        secondStage.initOwner(Main.mainStage);
        secondStage.initModality(Modality.APPLICATION_MODAL);
        Parent root = loadViewNodeHierarchy(view.fxml());
        Scene primarySence = new Scene(root, width, height); // ????????????
        secondStage.setScene(primarySence);
        secondStage.show();
    }

    public void setModule(){
        if(!"admin".equals(LoginController.loginName)) {
            Map<String, VBox> moduleMap = new HashMap<String, VBox>();
            moduleMap.put("????????????", wxManager);
            moduleMap.put("????????????", cxDown);
            moduleMap.put("????????????", txConnect);
            moduleMap.put("??????????????????", printer);
            moduleMap.put("??????????????????", bkTest);
            moduleMap.put("????????????", baseChange);
            moduleMap.put("????????????", wxCheck);


            List<Map<String, Object>> roleList = loginDao.getDataByModule(LoginController.loginName);
            if (ObjectUtil.isNotEmpty(roleList)) {
                Iterator iterators = moduleMap.entrySet().iterator();
                while (iterators.hasNext()) {
                    Map.Entry<String, VBox> a = (Map.Entry<String, VBox>) iterators.next();
                    a.getValue().setVisible(false);
                }

                for (int i = 0; i < roleList.size(); i++) {
                    Map<String, Object> a = roleList.get(i);
                    moduleMap.get(ObjectUtil.getString(a.get("module_name"))).setVisible(true);
                }
            }
        }
    }

}
