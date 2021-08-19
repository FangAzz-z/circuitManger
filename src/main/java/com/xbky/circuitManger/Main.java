package com.xbky.circuitManger;

import com.gn.decorator.GNDecorator;
import com.xbky.circuitManger.view.fx.FxmlView;
import com.xbky.circuitManger.view.fx.StageManager;
import com.xbky.circuitManger.view.fx.StageManagerSingleton;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
/*        primaryStage.getIcons().add(new Image("/assets/imgs/logo.png")); // 页面logo
        primaryStage.setTitle("产品维修管理软件"); // 页面标题
        Parent root = FXMLLoader.load(getClass().getResource("/views/baseSet_pt.fxml")); // 页面对应的fxml
        Scene primarySence = new Scene(root, 1600, 800); // 页面大小
        primaryStage.setScene( primarySence );
        primarySence.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());// 页面样式表
        primaryStage.show();*/

        GNDecorator decorator = new GNDecorator();
        StageManager stageManager = StageManagerSingleton.getSingleton(primaryStage, decorator);
        stageManager.switchScene(FxmlView.MAIN);
        stageManager.showDecorator();
    }



    public static void main(String[] args) {
        launch(args);
    }

}
