package com.xbky.circuitManger.utils;

import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class DialogUtil {

    public static File showChooseFileDialog(String title) {
        if (isBlank(title)) {
            title = "选择excel文件";
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Excel", "*.xlsx"),
                new FileChooser.ExtensionFilter("XLS", "*.xls"),
                new FileChooser.ExtensionFilter("XLSX", "*.xlsx"));

        return  fileChooser.showOpenDialog(new Stage());
    }

    public static File showExportFileDialog(String title) {

        if (isBlank(title)) {
            title = "选择导出目录";
        }

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(title);

        File directory = directoryChooser.showDialog(new Stage());
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        return directory;
    }


    public static void showAlertErrorMsgDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.titleProperty().set("错误信息提示");
        alert.headerTextProperty().set(message);

        alert.showAndWait();
    }

    public static void showAlertInfoMsgDialog(String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.titleProperty().set("信息提示");
        alert.headerTextProperty().set(info);

        alert.showAndWait();
    }

    private static  boolean isBlank(String input) {
        return input == null || input.trim().length() == 0;
    }

}
