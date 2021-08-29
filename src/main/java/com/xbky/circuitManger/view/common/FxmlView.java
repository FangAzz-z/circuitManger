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
    BASESET_INFO_DIALOG{
        @Override
        public String title() {return "添加";}
        @Override
        public String fxml() {return "/views/dialogs/baseSet_info_dialog.fxml";}
    },
    BASESET_USER{
        @Override
        public String title() {return "维修人员管理";}
        @Override
        public String fxml() {return "/views/baseSet_user.fxml";}
    },
    BASESET_USER_DIALOG{
        @Override
        public String title() {return "添加";}
        @Override
        public String fxml() {return "/views/dialogs/baseSet_user_dialog.fxml";}
    },
    BASESET_FITTING{
        @Override
        public String title() {return "配件入库信息";}
        @Override
        public String fxml() {return "/views/baseSet_fitting.fxml";}
    },
    BASESET_FITTING_DIALOG{
        @Override
        public String title() {return "添加";}
        @Override
        public String fxml() {return "/views/dialogs/baseSet_fitting_dialog.fxml";}
    },
    BASESET_PT_DIALOG{
        @Override
        public String title() {return "添加";}
        @Override
        public String fxml() {return "/views/dialogs/baseSet_pt_dialog.fxml";}
    },
    CHECK_MAINTAIN_DIALOG{
        @Override
        public String title() {return "添加";}
        @Override
        public String fxml() {return "/views/dialogs/check_maintain_dialog.fxml";}
    },
    CHECK_MAINTAIN{
        @Override
        public String title() {return "维修登记单";}
        @Override
        public String fxml() {return "/views/check_maintain.fxml";}
    },
    CHECK_FITTING{
        @Override
        public String title() {return "配件登记单";}
        @Override
        public String fxml() {return "/views/check_fitting.fxml";}
    },
    SYSTEM_DATA{
        @Override
        public String title() {return "数据备份";}
        @Override
        public String fxml() {return "/views/system_data.fxml";}
    },
    SYSTEM_INFO_CHANGE{
        @Override
        public String title() {return "软件管理使用方信息变更";}
        @Override
        public String fxml() {return "/views/system_info_change.fxml";}
    },
    SYSTEM_PRINTER{
        @Override
        public String title() {return "打印机设置";}
        @Override
        public String fxml() {return "/views/system_printer.fxml";}
    },
    PRODUCE_PRO{
        @Override
        public String title() {return "program程序调用";}
        @Override
        public String fxml() {return "/views/produce_programmm.fxml";}
    },
    PRODUCE_PTU{
        @Override
        public String title() {return "PTU程序调用";}
        @Override
        public String fxml() {return "/views/produce_ptu.fxml";}
    },
    PRODUCE_TERM{
        @Override
        public String title() {return "Term程序调用";}
        @Override
        public String fxml() {return "/views/produce_term.fxml";}
    },
    PRODUCE_PTOUCH{
        @Override
        public String title() {return "P-touchEditor程序调用";}
        @Override
        public String fxml() {return "/views/produce_ptouch.fxml";}
    }
    ;

    public abstract String title();
    public abstract String fxml();
}
