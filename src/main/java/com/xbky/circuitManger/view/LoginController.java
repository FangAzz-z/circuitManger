package com.xbky.circuitManger.view;

import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.view.common.FxmlView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private HBox hbPhoneLogin;

    @FXML
    private VBox vbPhoneLoginContainer;

    @FXML
    private VBox vbWxLoginContainer;

    @FXML
    private Text txtGetCode;

    @FXML
    private TextField tfPhone;

    @FXML
    private TextField tfCode;

    private Stage stage;

    @FXML
    private ImageView ivLoading;

    @FXML
    private ImageView ivScanCode;

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
            stage.hide();
            Stage primaryStage = Main.mainStage;
            primaryStage.setTitle("产品维修管理软件"); // 页面标题
            primaryStage.getIcons().add(new Image("/icons/logo2.png")); // 页面logo
            Parent root = FXMLLoader.load(getClass().getResource(FxmlView.MAIN.fxml())); // 页面对应的fxml
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
