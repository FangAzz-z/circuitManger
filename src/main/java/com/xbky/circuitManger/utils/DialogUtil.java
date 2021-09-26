package com.xbky.circuitManger.utils;

import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class DialogUtil {

    public static File showChooseExcelFileDialog(String title) {
        if (ObjectUtil.isBlank(title)) {
            title = "选择excel文件";
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Excel", "*.xls"),
                new FileChooser.ExtensionFilter("XLS", "*.xls"));

        return  fileChooser.showOpenDialog(new Stage());
    }

    public static File showExportFileDialog(String title) {

        if (ObjectUtil.isBlank(title)) {
            title = "选择导出目录";
        }

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(title);

        File directory = directoryChooser.showDialog(new Stage());
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        return directory;
    }

    public static File showExportExeFileDialog(String title) {

        if (ObjectUtil.isBlank(title)) {
            title = "选择程序目录";
        }

        FileChooser exeChooser = new FileChooser();
        exeChooser.setTitle(title);
        exeChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        exeChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("bat", "*.bat"),
                new FileChooser.ExtensionFilter("exe", "*.exe"));

        return  exeChooser.showOpenDialog(new Stage());
    }

    public static File showExportPicFileDialog(String title) {

        if (ObjectUtil.isBlank(title)) {
            title = "选择图片目录";
        }

        FileChooser exeChooser = new FileChooser();
        exeChooser.setTitle(title);
        exeChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        exeChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("pic", "*.jpg","*.jpeg","*.png","*.gif") );

        return  exeChooser.showOpenDialog(new Stage());
    }

    public static File showExportDbFileDialog(String title) {

        if (ObjectUtil.isBlank(title)) {
            title = "选择图片目录";
        }

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(title);

        File directory = directoryChooser.showDialog(new Stage());
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        return directory;
    }
}
