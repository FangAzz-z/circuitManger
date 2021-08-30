package com.xbky.circuitManger;

import com.xbky.circuitManger.view.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginManager {
    private static Scene scene;

    public static void  loadLogin(){
        try {
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.initOwner(Main.mainStage);
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(LoginManager.class.getResource("/views/login.fxml"));
            Parent root = loader.<Parent>load();
            LoginController lc = loader.<LoginController>getController();
            lc.setStage(stage);
            root.setLayoutY(70.0D);
            stage.setResizable(false);
            scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            stage.setX(Main.mainStage.getX() + Main.mainStage.getWidth() / 2.0D - stage.getScene().getWidth() / 2.0D);
            stage.setY(Main.mainStage.getY() + Main.mainStage.getHeight() / 2.0D - stage.getScene().getHeight() / 2.0D);
        } catch (Exception e) {

        }
    }
}
