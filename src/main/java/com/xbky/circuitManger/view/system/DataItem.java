package com.xbky.circuitManger.view.system;

import com.xbky.circuitManger.view.common.StageManager;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class DataItem {
    public String  id;
    public String  dataSource;
    public javafx.scene.control.Button button;

    public DataItem(String id, String dataSource) {
        this.id = id;
        this.dataSource = dataSource;
        button = new Button("备份");
        button.setStyle("-fx-background-color: #509fe3;-fx-text-fill: white;");
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-border-color: #265a87;-fx-background-color: #286090;-fx-text-fill: white;");
        });
        button.setOnMouseExited(event -> {
            button.setStyle("-fx-background-color: #509fe3;-fx-text-fill: white;");
        });
        button.setOnMousePressed(event -> {
            button.setStyle("-fx-background-color: #3774a8;-fx-border-color: #265a87;-fx-effect: innershadow(gaussian, #265986, 10, 0, 0, 3);-fx-text-fill: white;");});
        button.setOnMouseReleased(event -> {
            button.setStyle("-fx-background-color: #509fe3;-fx-text-fill: white;");});
        button.setAlignment(Pos.CENTER);
        button.setOnAction(event -> {
            StageManager.nullWarn(dataSource+"备份成功!");
        });
    }
}
