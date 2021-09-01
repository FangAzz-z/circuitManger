package com.xbky.circuitManger.view.baseSetUser;

import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.dao.MaintainUserDao;
import com.xbky.circuitManger.entity.MaintainUser;
import com.xbky.circuitManger.entity.ProductType;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.view.baseSetPt.BaseSetPtAddController;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class BaseSetUserController implements Initializable {

    @FXML
    public TableColumn id;
    @FXML
    public TableColumn name;
    @FXML
    public TableColumn sex;
    @FXML
    public TableColumn department;
    @FXML
    public TableColumn job;
    @FXML
    public TableColumn phone;
    @FXML
    public TableView userTable;

    @FXML
    public TextField textName;
    @FXML
    public ToggleGroup gender;
    @FXML
    public TextField textDepartment;
    @FXML
    public TextField textJob;
    @FXML
    public TextField textPhone;
    @FXML
    public RadioButton rbAll;


    MaintainUserDao dao = new MaintainUserDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.id.setCellValueFactory(new MapValueFactory<String>("id"));
        this.name.setCellValueFactory(new MapValueFactory<String>("name"));
        this.sex.setCellValueFactory(new  MapValueFactory<String>("sex"));
        this.department.setCellValueFactory(new MapValueFactory<String>("department"));
        this.job.setCellValueFactory(new MapValueFactory<String>("job"));
        this.phone.setCellValueFactory(new MapValueFactory<String>("phone"));
        refreshData();
    }

    public void addData(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.BASESET_USER_DIALOG.fxml()));
        Parent root = loader.<Parent>load();
        Scene scene = new Scene(root, 500, 500); // 页面大小
        // StageManager.initStyle(dialog);
        Stage dialog = new Stage();
        dialog.setTitle("维修人员-添加");
        dialog.getIcons().add(new Image("/icons/logo2.png"));
        dialog.setScene(scene);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initOwner(Main.mainStage);
        dialog.centerOnScreen();
        BaseSetUserAddController controller = loader.getController();
        controller.setDialog(dialog);
        controller.setResultHandle(()->{refreshData();});
        dialog.show();
    }

    private void refreshData() {
        ObservableList<Map<String,Object>> list = FXCollections.observableArrayList();
        List<Map<String,Object>> dataList =  dao.commonQueryAll("CM_MAINTAIN_USER");
        list.addAll(dataList);
        this.userTable.getSelectionModel().clearSelection();
        this.userTable.setItems(list);
        this.userTable.refresh();
    }

    public void modifyData(ActionEvent actionEvent) throws IOException {
        Map<String,Object> map = (Map)userTable.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            StageManager.nullWarn();
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.BASESET_USER_DIALOG.fxml()));
        Parent root = loader.load();
        Scene scene = new Scene(root, 500, 500); // 页面大小
        // StageManager.initStyle(dialog);
        Stage dialog = new Stage();
        dialog.setTitle("维修人员-修改");
        dialog.setScene(scene);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initOwner(Main.mainStage);
        dialog.centerOnScreen();
        BaseSetUserAddController  controller =  loader.getController();
        controller.setDialog(dialog);
        controller.setBaseData(new MaintainUser((Long)map.get("id"),(String)map.get("name"),(String)map.get("sex"),(String)map.get("department"),(String)map.get("job"),(String)map.get("phone")));
        controller.setResultHandle(()->{refreshData();});
        dialog.show();
    }

    public void deleteData(ActionEvent actionEvent) {
        Map<String,Object> map = (Map)userTable.getSelectionModel().getSelectedItem();
        if (ObjectUtil.isNull(map)) {
            StageManager.nullWarn();
            return;
        }
        if(StageManager.deleteTrue()) {
            dao.commonDeleteById("CM_MAINTAIN_USER", map.get("id") + "");
            refreshData();
        }
    }

    public void reset(ActionEvent actionEvent) {
        this.textName.clear();
        this.textDepartment.clear();
        this.textJob.clear();
        this.textPhone.clear();
        this.rbAll.setSelected(true);
    }

    public void query(ActionEvent actionEvent) {
        String radioValue = ((RadioButton)this.gender.getSelectedToggle()).getAccessibleText();
        if (ObjectUtil.isAllNull(this.textName.getText(), this.textDepartment.getText(), this.textJob.getText())&&"全部".equals(radioValue)) {
            refreshData();
            return;
        }
        MaintainUser mu = new MaintainUser();
        mu.setName(this.textName.getText());
        mu.setDepartment(this.textDepartment.getText());
        mu.setJob(this.textJob.getText());
        mu.setPhone(this.textPhone.getText());
        if(!"全部".equals(radioValue)){
            mu.setSex(radioValue);
        }

        ObservableList<Map<String,Object>> list = FXCollections.observableArrayList();
        List<Map<String,Object>> dataList = this.dao.queryByExample(mu);
        list.addAll(dataList);
        this.userTable.getSelectionModel().clearSelection();
        this.userTable.setItems(list);
        this.userTable.refresh();
    }
}