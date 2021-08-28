package com.xbky.circuitManger.view.common;

import com.xbky.circuitManger.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Objects;
import java.util.Optional;

public class StageManager {
    private static Logger log = LoggerFactory.getLogger(StageManager.class);
    public static  Parent loadViewNode(String fxmlFilePath) {
        Parent rootNode = null;
        try {
           URL url =  StageManager.class.getResource(fxmlFilePath);

            rootNode = FXMLLoader.load(StageManager.class.getResource(fxmlFilePath));
            Objects.requireNonNull(rootNode, "A Root FXML node must not be null");
        } catch (Exception exception) {
            log.info("", exception);
        }
        return rootNode;
    }
    public static void initStyle(Stage stage){

        stage.getScene().getStylesheets().addAll(StageManager.class.getResource("/css/theme/fonts.css").toExternalForm(),
                StageManager.class.getResource("/css/theme/light.css").toExternalForm(),
                StageManager.class.getResource("/css/theme/bootstrap.css").toExternalForm(),
                StageManager.class.getResource("/css/theme/shape.css").toExternalForm(),
                StageManager.class.getResource("/css/theme/skeleton.css").toExternalForm(),
                StageManager.class.getResource("/css/theme/drawer.css").toExternalForm());
    }
    public static void nullWarn(){
        nullWarn("提示","请选中某一行");
    }
    public static void nullWarn(String content){
        nullWarn("提示",content);
    }
    public static void nullWarn(String title,String context) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(context);
        alert.setHeaderText("");
        alert.initOwner(Main.mainStage);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == javafx.scene.control.ButtonType.OK){
            alert.close();
        }
    }
}
