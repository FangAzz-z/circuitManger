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
        public String title() {return "维修产品类别型号";}
        @Override
        public String fxml() {return "/views/baseSet_pt.fxml";}
    },
    BASESET_INFO{
        @Override
        public String title() {return "维修基础信息";}
        @Override
        public String fxml() {return "/views/baseSet_info.fxml";}
    },
    BASESET_USER{
        @Override
        public String title() {return "维修人员管理";}
        @Override
        public String fxml() {return "/views/baseSet_user.fxml";}
    };

    public abstract String title();
    public abstract String fxml();
}
