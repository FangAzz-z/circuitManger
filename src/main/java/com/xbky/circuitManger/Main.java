package com.xbky.circuitManger;

import com.xbky.circuitManger.view.common.FxmlView;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.util.Optional;

public class Main extends Application {

    public static Stage mainStage = null;

    @Override
    public void start(Stage primaryStag) throws Exception{
        AppIntializer.init();
        Stage primaryStage = new Stage();
        mainStage = primaryStage;
     //  LoginManager.loadLogin();
        primaryStage.setTitle("产品维修管理软件"); // 页面标题
        primaryStage.getIcons().add(new Image("/icons/logo2.png")); // 页面logo
        Parent root = FXMLLoader.load(getClass().getResource("/views/appMain.fxml")); // 页面对应的fxml
        Scene primarySence = new Scene(root, 1800, 900); // 页面大小
        primaryStage.setScene( primarySence );
        primaryStage.setOnCloseRequest(event -> {
            closeAllPopups(event);
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void closeAllPopups(Event event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("提示");
        alert.setContentText("确定退出电路板维修管理系统？");
        alert.setHeaderText("");
        alert.initOwner(mainStage);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //始终屏幕居中显示
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight();
        alert.setX(width/2-200);
        alert.setY(height/2-50);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == javafx.scene.control.ButtonType.OK){
            System.exit(0);
        }else{
            event.consume();
        }
    }

}
