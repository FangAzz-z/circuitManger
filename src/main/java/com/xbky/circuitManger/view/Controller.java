package com.xbky.circuitManger.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public Pane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        String imageUrl = getClass().getResource("/icons/bj.jpeg").toExternalForm();
//        BackgroundImage myBI= new BackgroundImage(new Image(imageUrl,600,400,false,true),
//                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
//                BackgroundSize.DEFAULT);
//        pane.setBackground(new Background(myBI));
    }
}
