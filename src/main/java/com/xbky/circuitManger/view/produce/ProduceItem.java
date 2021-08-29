package com.xbky.circuitManger.view.produce;

import com.xbky.circuitManger.view.common.StageManager;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class ProduceItem {
    public String id;
    public String produceName;
    public String produceType;
    public String produceUrl;
    public Button button;

    public ProduceItem(String id, String produceType, String produceName,String produceUrl) {
        this.id = id;
        this.produceType = produceType;
        this.produceName = produceName;
        this.produceUrl = produceUrl;
        button = new Button("执行");
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
            StageManager.nullWarn(produceName+"执行中....");
        });
    }
}
