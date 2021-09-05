package com.xbky.circuitManger.view.system;

import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.dao.SystemUserDao;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.view.common.FxmlView;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class SystemUserController implements Initializable {

    @FXML
    public TableView userTable;
    @FXML
    public TableColumn id;
    @FXML
    public TableColumn userName;
    @FXML
    public TableColumn userPassword;
    @FXML
    public TextField tfName;

    SystemUserDao dao = new SystemUserDao();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.id.setCellValueFactory(new MapValueFactory<String>("id"));
        this.userName.setCellValueFactory(new MapValueFactory<String>("user_name"));
        this.userPassword.setCellValueFactory(new  MapValueFactory<String>("user_password"));
        refreshData();
    }

    public void reset(ActionEvent actionEvent) {
        this.tfName.clear();
    }

    public void query(ActionEvent actionEvent) {
        if (ObjectUtil.isAllNull(this.tfName.getText())) {
            refreshData();
            return;
        }
        ObservableList<Map<String,Object>> list = FXCollections.observableArrayList();
        List<Map<String,Object>> dataList = this.dao.getUserByName(this.tfName.getText());
        list.addAll(dataList);
        this.userTable.getSelectionModel().clearSelection();
        this.userTable.setItems(list);
        this.userTable.refresh();
    }

    public void addData(ActionEvent actionEvent) throws IOException  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.SYSTEM_USER_DIALOG.fxml()));
        Parent root = loader.load();
        Scene scene = new Scene(root, 500, 500); // 页面大小
        Stage dialog = new Stage();
        dialog.setScene(scene);
        dialog.setTitle("系统用户管理-添加");
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initOwner(Main.mainStage);
        dialog.centerOnScreen();
        SystemUserAddController controller = loader.getController();
        controller.setDialog(dialog);
        controller.setResultHandle(()->{refreshData();});
        dialog.show();
    }

    public void modifyData(ActionEvent actionEvent) throws IOException {
        Map<String,Object> map = (Map)userTable.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            StageManager.nullWarn();
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.SYSTEM_USER_DIALOG.fxml()));
        Parent root = loader.load();
        Scene scene = new Scene(root, 500, 600); // 页面大小
        Stage dialog = new Stage();
        dialog.setScene(scene);
        dialog.setTitle("系统用户管理-修改");
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initOwner(Main.mainStage);
        dialog.centerOnScreen();
        SystemUserAddController  controller = loader.getController();
        controller.setDialog(dialog);
        controller.setBaseData((String)map.get("id"),(String)map.get("user_name"),(String)map.get("user_password"));
        controller.setResultHandle(()->{refreshData();});
        dialog.show();
    }

    public void deleteData(ActionEvent actionEvent) {
        Map<String,Object> map = (Map)userTable.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            StageManager.nullWarn();
            return;
        }
        if("admin".equals(ObjectUtil.getString(map.get("user_name")))){
            StageManager.nullWarn("admin用户不能删除");
            return;
        }
        if(StageManager.deleteTrue()) {
            dao.commonDeleteById("CM_SYSTEM_USER", map.get("id") + "");
            refreshData();
        }
    }

    public void refreshData(){
        ObservableList<Map<String,Object>> list = FXCollections.observableArrayList();
        List<Map<String,Object>> dataList =  dao.commonQueryAll("CM_SYSTEM_USER");
        list.addAll(dataList);
        this.userTable.getSelectionModel().clearSelection();
        this.userTable.setItems(list);
        this.userTable.refresh();
    }
}
