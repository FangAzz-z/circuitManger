package com.xbky.circuitManger.view.common;


public enum FxmlView {

    MAIN{
        @Override
        public String title() {return "初始页";}
        @Override
        public String fxml() {return "/views/main.fxml";}
    },
    BASESET_PT{
        @Override
        public String title() {return "产品类型设置";}
        @Override
        public String fxml() {return "/views/baseSet_pt.fxml";}
    };

    public abstract String title();
    public abstract String fxml();
}
