package com.xbky.circuitManger;

import com.xbky.circuitManger.common.StartUp;
import com.xbky.circuitManger.dao.ProductTypeDao;
import com.xbky.circuitManger.entity.ProductType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../../sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        new StartUp().init();
        List<ProductType> list = new ProductTypeDao().queryAll();
        list.forEach(a->System.out.println(a.getCategory()+"-"+a.getModel()+"-"+a.getBrand()));
    }


    public static void main(String[] args) {
        launch(args);
    }

}
