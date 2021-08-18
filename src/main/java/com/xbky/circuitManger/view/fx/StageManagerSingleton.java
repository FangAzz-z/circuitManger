package com.xbky.circuitManger.view.fx;

import com.gn.decorator.GNDecorator;
import javafx.stage.Stage;

public class StageManagerSingleton {

    private volatile static  StageManager stageManager;

    public static StageManager getSingleton(Stage stage, GNDecorator decorator) {
            if (stageManager == null) {
                 synchronized (StageManagerSingleton.class) {
                    if (stageManager == null) {
                        stageManager = new StageManager(stage,decorator);
                        }
                 }
           }
           return stageManager;
    }
    public static StageManager getSingleton() {
        return stageManager;
    }
}
