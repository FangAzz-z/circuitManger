package com.xbky.circuitManger.view.common;

import com.xbky.circuitManger.Main;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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

    public static boolean deleteTrue() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("提示");
        alert.setContentText("确定要删除这一条记录吗");
        alert.setHeaderText("");
        alert.initOwner(Main.mainStage);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == javafx.scene.control.ButtonType.OK){
           return true;
        }else{
            return false;
        }
    }

    public static void infoWarn(String msg) {
        Stage window = new Stage();
        window.setTitle("信息");
                window.setResizable(false);
        window.initOwner(Main.mainStage);
        window.getIcons().add(new Image("icons/logo2.png"));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(300.0D);
        Button button = new Button("关闭");
                button.setOnAction(e -> window.close());
        Label label = new Label(msg);
        Separator sp = new Separator();
        VBox layout = new VBox(5.0D);
        HBox hLayout = new HBox();
        layout.getChildren().addAll(new Node[] { label, sp, hLayout });
        VBox.setMargin(label, new Insets(10.0D, 10.0D, 0.0D, 10.0D));
        VBox.setMargin(hLayout, new Insets(0.0D, 0.0D, 10.0D, 0.0D));
        HBox.setMargin(button, new Insets(0.0D, 10.0D, 0.0D, 0.0D));
        hLayout.getChildren().add(button);
        hLayout.setAlignment(Pos.CENTER_RIGHT);
        layout.setAlignment(Pos.CENTER_LEFT);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.setX(Main.mainStage.getX() + Main.mainStage.getWidth() / 2.0D - 200.0D);
        window.setY(Main.mainStage.getY() + Main.mainStage.getHeight() / 2.0D - 38.0D);
        window.show();
    }
}
