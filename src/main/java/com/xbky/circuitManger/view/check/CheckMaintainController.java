package com.xbky.circuitManger.view.check;

import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.view.common.FxmlView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CheckMaintainController implements Initializable {
    @FXML
    public ComboBox queryModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String week_days[] =
                { "Monday", "Tuesday", "Wednesday",
                        "Thrusday", "Friday" };
        queryModel.setItems(FXCollections
                .observableArrayList(week_days));
    }

    public void addData(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.CHECK_MAINTAIN_DIALOG.fxml()));
        Parent root = loader.load();
        Scene scene = new Scene(root, 750, 600); // 页面大小
        // StageManager.initStyle(dialog);
        Stage dialog = new Stage();
        dialog.setTitle("维修登记单-添加"); // 页面标题
        dialog.setScene(scene);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initOwner(Main.mainStage);
        dialog.centerOnScreen();
        CheckMaintainAddController controller = loader.getController();
        controller.setDialog(dialog);
        controller.setResultHandle(()->{refreshData();});
        dialog.show();
    }

    private void refreshData() {
    }

    public void modifyData(ActionEvent actionEvent) {
    }

    public void deleteData(ActionEvent actionEvent) {
    }

    public void reset(ActionEvent actionEvent) {
    }

    public void query(ActionEvent actionEvent) {
    }
}
