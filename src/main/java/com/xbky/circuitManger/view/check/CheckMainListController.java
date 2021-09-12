package com.xbky.circuitManger.view.check;

import com.xbky.circuitManger.utils.ObjectUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class CheckMainListController implements Initializable {

    @FXML
    public ListView listView;



    private  static Stage dialog = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void printLabel(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
        if(dialog!=null) {
            dialog.close();
        }
    }

    public void setListItems(Map<String,Object> map) {
        String str = "id:"+ObjectUtil.getString(map.get("id"));
        String str1 = "维修单号:" + ObjectUtil.getString(map.get("maintain_id"));
        String str2 = "接收日期:" + ObjectUtil.getString(map.get("receive_date"));
        String str3 = "完成日期:" + ObjectUtil.getString(map.get("complete_date"));
        String str4 = "维修卡编号:" + ObjectUtil.getString(map.get("maintain_card_no"));
        String str5 = "产品型号:" + ObjectUtil.getString(map.get("maintain_card_model"));
        String str6 = "产品品牌:" + ObjectUtil.getString(map.get("maintain_card_brand"));
        String str66 = "产品类别:" + ObjectUtil.getString(map.get("maintain_card_category"));
        String str7 = "维修人员:" + ObjectUtil.getString(map.get("maintain_user"));
        String str8 = "维修状态:" + ObjectUtil.getString(map.get("wx_status"));
        String str9 = "故障现象:" + ObjectUtil.getString(map.get("wx_show"));
        String str10 = "维修措施:" + ObjectUtil.getString(map.get("wx_method"));
        String str11 = "维修结果:" + ObjectUtil.getString(map.get("wx_result"));
        String str12 = "维修记录:" + ObjectUtil.getString(map.get("maintain_desc"));
        String str13 = "维修配件:" + ObjectUtil.getString(map.get("maintain_fitting"));
        List<String> strList = new ArrayList<>();
        strList.add(str);
        strList.add(str1);
        strList.add(str2);
        strList.add(str3);
        strList.add(str4);
        strList.add(str5);
        strList.add(str6);
        strList.add(str66);
        strList.add(str7);
        strList.add(str8);
        strList.add(str9);
        strList.add(str10);
        strList.add(str11);
        strList.add(str12);
        strList.add(str13);
        listView.setItems(FXCollections.observableArrayList(strList));
    }

    public static Stage getDialog() {
        return dialog;
    }

    public static void setDialog(Stage dialog) {
        CheckMainListController.dialog = dialog;
    }
}
