package com.xbky.circuitManger.view.common;


public enum FxmlView {

    MAIN{
        @Override
        public String title() {return "初始页";}
        @Override
        public String fxml() {return "/views/appMain.fxml";}
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
    BASESET_INFO_DIALOG_V2{
        @Override
        public String title() {return "添加";}
        @Override
        public String fxml() {return "/views/dialogs/baseSet_info_dialogV2.fxml";}
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
    CHECK_FITTING_CHOOSE{
        @Override
        public String title() {return "配件选择";}
        @Override
        public String fxml() {return "/views/dialogs/check_fitting_choose.fxml";}
    },
    CHECK_FITTING_CLEAR{
        @Override
        public String title() {return "配件删除";}
        @Override
        public String fxml() {return "/views/dialogs/check_fitting_clear.fxml";}
    },
    CHECK_MAINTAIN{
        @Override
        public String title() {return "维修登记单";}
        @Override
        public String fxml() {return "/views/check_maintain.fxml";}
    },
    CHECK_FITTING_DIALOG{
        @Override
        public String title() {return "配件登记单-添加";}
        @Override
        public String fxml() {return "/views/dialogs/check_fitting_dialog.fxml";}
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
    MAIN_BASE_SET{
        @Override
        public String title() {return "基础设置";}
        @Override
        public String fxml() {return "/views/mainBaseSet.fxml";}
    },
    MAIN_CHECK{
        @Override
        public String title() {return "维修登记单";}
        @Override
        public String fxml() {return "/views/mainCheck.fxml";}
    },
    MAIN_SYSTEM{
        @Override
        public String title() {return "系统维护";}
        @Override
        public String fxml() {return "/views/mainSystem.fxml";}
    },
    SYSTEM_USER{
        @Override
        public String title() {return "系统用户管理";}
        @Override
        public String fxml() {return "/views/system_user.fxml";}
    },
    SYSTEM_USER_ROLE{
        @Override
        public String title() {return "用户权限管理";}
        @Override
        public String fxml() {return "/views/system_user_role.fxml";}
    },
    SYSTEM_USER_DIALOG{
        @Override
        public String title() {return "添加";}
        @Override
        public String fxml() {return "/views/dialogs/system_user_dialog.fxml";}
    },
    SYSTEM_USER_ROLE_DIALOG{
        @Override
        public String title() {return "添加";}
        @Override
        public String fxml() {return "/views/dialogs/system_user_role_dialog.fxml";}
    }
    ;

    public abstract String title();
    public abstract String fxml();
}
