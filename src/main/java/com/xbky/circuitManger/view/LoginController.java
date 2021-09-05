package com.xbky.circuitManger.view;

import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.dao.SystemUserDao;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.view.common.FxmlView;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    public PasswordField tfPassword;
    @FXML
    public TextField tfName;

    private Stage stage;

    SystemUserDao dao = new SystemUserDao();

    public static  String loginName;

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void closeWindow(MouseEvent mouseEvent) {
    }

    public void loginSystem(MouseEvent mouseEvent) {
        try {
            if(ObjectUtil.hasNull(tfName.getText(),tfPassword.getText())){
                StageManager.nullWarn("用户名或密码不能为空",stage);
                return;
            }
            List<Map<String,Object>> loginList= dao.getUserByName(tfName.getText());
            if(ObjectUtil.isEmpty(loginList)){
                StageManager.nullWarn("用户不存在",stage);
                return;
            }
            if(!tfPassword.getText().equals(ObjectUtil.getString(loginList.get(0).get("user_password")))){
                StageManager.nullWarn("密码错误",stage);
                return;
            }
            loginName = tfName.getText();
            stage.hide();
            Stage primaryStage = Main.mainStage;
            primaryStage.setTitle("产品维修管理软件"); // 页面标题
            primaryStage.getIcons().add(new Image("/icons/logo2.png")); // 页面logo
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.MAIN.fxml()));
            Parent root = loader.load(getClass().getResource(FxmlView.MAIN.fxml())); // 页面对应的fxml
//            MainController controller = loader.getController();
//            controller.setModule();
            Scene primarySence = new Scene(root, 1600, 800); // 页面大小
            primaryStage.setScene(primarySence);
            primaryStage.setOnCloseRequest(event -> {
                Main.closeAllPopups();
            });
            primaryStage.show();
        } catch (Exception e) {

        }
    }
}
