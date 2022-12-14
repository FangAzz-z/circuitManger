package com.xbky.circuitManger.view.produce;

import com.sun.javafx.scene.layout.region.Margins;
import com.xbky.circuitManger.utils.DialogUtil;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.File;

public class ProduceItem {
    public String id;
    public String produceName;
    public String produceUrl;
    public Button urlSet;
    public Button button;

    public ProduceItem(String id, final String produceName, String produceUrl) {
        this.id = id;
        this.produceName = produceName;
        this.produceUrl = produceUrl;
        button = new Button("执行");
        button.setStyle("-fx-background-color: #509fe3;-fx-text-fill: white;");
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setStyle("-fx-border-color: #265a87;-fx-background-color: #286090;-fx-text-fill: white;");
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setStyle("-fx-background-color: #509fe3;-fx-text-fill: white;");
            }
        });
        button.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setStyle("-fx-background-color: #3774a8;-fx-border-color: #265a87;-fx-effect: innershadow(gaussian, #265986, 10, 0, 0, 3);-fx-text-fill: white;");
            }
        });
        button.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setStyle("-fx-background-color: #509fe3;-fx-text-fill: white;");
            }
        });
        button.setAlignment(Pos.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StageManager.nullWarn(produceName + "执行中....");
            }
        });

        urlSet = new Button("地址更换");
        urlSet.setStyle("-fx-background-color: #509fe3;-fx-text-fill: white;");
        urlSet.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                urlSet.setStyle("-fx-border-color: #265a87;-fx-background-color: #286090;-fx-text-fill: white;");
            }
        });
        urlSet.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                urlSet.setStyle("-fx-background-color: #509fe3;-fx-text-fill: white;");
            }
        });
        urlSet.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                urlSet.setStyle("-fx-background-color: #3774a8;-fx-border-color: #265a87;-fx-effect: innershadow(gaussian, #265986, 10, 0, 0, 3);-fx-text-fill: white;");
            }
        });
        urlSet.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                urlSet.setStyle("-fx-background-color: #509fe3;-fx-text-fill: white;");
            }
        });
        urlSet.setAlignment(Pos.CENTER);
        urlSet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ProduceItem.this.chooseAddr();
            }
        });
    }

    private  void chooseAddr(){
        File exePathFile = DialogUtil.showExportExeFileDialog("请选择程序路径");
        if (exePathFile != null) {
        }
    }
}
