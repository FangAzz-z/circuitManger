package com.xbky.circuitManger.view.system;

import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.common.enums.ModuleEnum;
import com.xbky.circuitManger.dao.SystemUserRoleDao;
import com.xbky.circuitManger.entity.SystemUserRole;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SystemUserRoleController implements Initializable {
    @FXML
    public ComboBox comboUser;
    @FXML
    public ComboBox comboModule;
    @FXML
    public TableView userTable;
    @FXML
    public TableColumn id;
    @FXML
    public TableColumn userName;
    @FXML
    public TableColumn moduleName;

    SystemUserRoleDao dao = new SystemUserRoleDao();

    private  static Stage dialog = null;

    private  static Runnable resultHandle = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.id.setCellValueFactory(new MapValueFactory<String>("id"));
        this.userName.setCellValueFactory(new MapValueFactory<String>("user_name"));
        this.moduleName.setCellValueFactory(new MapValueFactory<String>("module_name"));
        refreshData();
        List<String> user = dao.commonQueryAll("CM_SYSTEM_USER").stream().map(a->((String)a.get("user_name"))).filter(a->!"admin".equals(a)).collect(Collectors.toList());
        List<String> module = Arrays.stream(ModuleEnum.values()).map(a->a.getName()).collect(Collectors.toList());
        comboUser.setItems(FXCollections.observableArrayList(user));
        comboModule.setItems(FXCollections.observableArrayList(module));
    }

    public void reset(ActionEvent actionEvent) {
        this.comboUser.getSelectionModel().clearSelection();
        this.comboModule.getSelectionModel().clearSelection();
    }

    public void query(ActionEvent actionEvent) {
        SystemUserRole record = new SystemUserRole();
        if(this.comboUser.getSelectionModel().getSelectedItem()!=null) {
            record.setUserName(this.comboModule.getSelectionModel().getSelectedItem().toString());
        }
        if(this.comboModule.getSelectionModel().getSelectedItem()!=null) {
            record.setModuleName(this.comboModule.getSelectionModel().getSelectedItem().toString());
        }

        ObservableList<Map<String,Object>> list = FXCollections.observableArrayList();
        List<Map<String,Object>> dataList = this.dao.queryByExample(record);
        list.addAll(dataList);
        this.userTable.getSelectionModel().clearSelection();
        this.userTable.setItems(list);
        this.userTable.refresh();
    }

    public void addData(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.SYSTEM_USER_ROLE_DIALOG.fxml()));
        Parent root = loader.load();
        Scene scene = new Scene(root, 500, 600); // 页面大小
        Stage dialog = new Stage();
        dialog.setScene(scene);
        dialog.setTitle("系统用户管理-修改");
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initOwner(Main.mainStage);
        dialog.centerOnScreen();
        SystemUserRoleAddController  controller = loader.getController();
        SystemUserRoleAddController.setDialog(dialog);
        SystemUserRoleAddController.setResultHandle(new Runnable() {
            @Override
            public void run() {
                refreshData();
            }
        });
        dialog.show();
    }

    public void deleteData(ActionEvent actionEvent) {
        Map<String,Object> map = (Map)userTable.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            StageManager.nullWarn();
            return;
        }
        if(StageManager.deleteTrue()) {
            dao.commonDeleteById("CM_SYSTEM_USER_MODULE", map.get("id") + "");
            refreshData();
        }
    }

    public void refreshData(){
        ObservableList<Map<String,Object>> list = FXCollections.observableArrayList();
        List<Map<String,Object>> dataList =  dao.commonQueryAll("CM_SYSTEM_USER_MODULE");
        list.addAll(dataList);
        this.userTable.getSelectionModel().clearSelection();
        this.userTable.setItems(list);
        this.userTable.refresh();
    }


}
