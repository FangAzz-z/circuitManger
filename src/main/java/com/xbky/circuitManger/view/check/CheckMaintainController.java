package com.xbky.circuitManger.view.check;

import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.dao.BaseInfoDao;
import com.xbky.circuitManger.dao.CheckFittingRecordDao;
import com.xbky.circuitManger.dao.MaintainUserDao;
import com.xbky.circuitManger.dao.ProductTypeDao;
import com.xbky.circuitManger.view.common.FxmlView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CheckMaintainController implements Initializable {
    @FXML
    public ComboBox queryModel;
    @FXML
    public TableColumn id;
    @FXML
    public TableColumn maintainId;
    @FXML
    public TableColumn receiveDate;
    @FXML
    public TableColumn completeDate;
    @FXML
    public TableColumn maintainCardNo;
    @FXML
    public TableColumn maintainCardModel;
    @FXML
    public TableColumn maintainCardBrand;
    @FXML
    public TableColumn maintainCardCategory;
    @FXML
    public TableColumn maintainUser;
    @FXML
    public TableColumn wxStatus;
    @FXML
    public TableColumn wxShow;
    @FXML
    public TableColumn wxMethod;
    @FXML
    public TableColumn wxResult;
    @FXML
    public TableColumn maintainDesc;
    @FXML
    public TableColumn maintainFitting;

    BaseInfoDao baseInfoDao = new BaseInfoDao();
    ProductTypeDao productTypeDao = new ProductTypeDao();
    MaintainUserDao maintainUserDao = new MaintainUserDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.id.setCellValueFactory(new MapValueFactory<String>("id"));
        this.maintainId.setCellValueFactory(new MapValueFactory<String>("maintainId"));
        this.receiveDate.setCellValueFactory(new  MapValueFactory<String>("receiveDate"));
        this.completeDate.setCellValueFactory(new MapValueFactory<String>("completeDate"));
        this.maintainCardNo.setCellValueFactory(new MapValueFactory<String>("maintainCardNo"));
        this.maintainCardModel.setCellValueFactory(new MapValueFactory<String>("maintainCardModel"));
        this.maintainCardBrand.setCellValueFactory(new MapValueFactory<String>("maintainCardBrand"));
        this.maintainCardCategory.setCellValueFactory(new MapValueFactory<>("maintainCardCategory"));
        this.maintainUser.setCellValueFactory(new MapValueFactory<>("maintainUser"));
        this.wxStatus.setCellValueFactory(new MapValueFactory<>("wxStatus"));
        this.wxShow.setCellValueFactory(new MapValueFactory<>("wxShow"));
        this.wxMethod.setCellValueFactory(new MapValueFactory<>("wxMethod"));
        this.wxResult.setCellValueFactory(new MapValueFactory<>("wxResult"));
        this.maintainDesc.setCellValueFactory(new MapValueFactory<>("maintainDesc"));
        this.maintainFitting.setCellValueFactory(new MapValueFactory<>("maintainFitting"));
        refreshData();

        List<String> statusList =  baseInfoDao.commonQueryAll("CM_BASE_PT_STATUS").stream().map(a->((String)a.get("content"))).collect(Collectors.toList());
        List<String> showList =  baseInfoDao.commonQueryAll("CM_BASE_FAULT_SHOW").stream().map(a->((String)a.get("content"))).collect(Collectors.toList());
        List<String> methodList =  baseInfoDao.commonQueryAll("CM_BASE_MAINTAIN_METHOD").stream().map(a->((String)a.get("content"))).collect(Collectors.toList());
        List<String> resultList =  baseInfoDao.commonQueryAll("CM_BASE_HANDLE_RESULT").stream().map(a->((String)a.get("content"))).collect(Collectors.toList());
        List<String> userName = maintainUserDao.commonQueryAll("CM_MAINTAIN_USER").stream().map(a->(a.get("department")+((String)a.get("name")))).collect(Collectors.toList());

        List<String> category = maintainUserDao.commonQueryAll("CM_PRODUCT_TYPE").stream().map(a->((String)a.get("category"))).collect(Collectors.toList());
        List<String> model = maintainUserDao.commonQueryAll("CM_PRODUCT_TYPE").stream().map(a->((String)a.get("model"))).collect(Collectors.toList());
        List<String> brand = maintainUserDao.commonQueryAll("CM_PRODUCT_TYPE").stream().map(a->((String)a.get("brand"))).collect(Collectors.toList());

        String week_days[] =
                { "Monday", "Tuesday", "Wednesday",
                        "Thrusday", "Friday" };
        queryModel.setItems(FXCollections
                .observableArrayList(week_days));
    }

    public void addData(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlView.CHECK_MAINTAIN_DIALOG.fxml()));
        Parent root = loader.load();
        Scene scene = new Scene(root, 750, 600); // 页面大小
        // StageManager.initStyle(dialog);
        Stage dialog = new Stage();
        dialog.setTitle("维修登记单-添加"); // 页面标题
        dialog.setScene(scene);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.initOwner(Main.mainStage);
        dialog.centerOnScreen();
        CheckMaintainAddController controller = loader.getController();
        controller.setDialog(dialog);
        controller.setResultHandle(()->{refreshData();});
        dialog.show();
    }

    private void refreshData() {
    }

    public void modifyData(ActionEvent actionEvent) {
    }

    public void deleteData(ActionEvent actionEvent) {
    }

    public void reset(ActionEvent actionEvent) {
    }

    public void query(ActionEvent actionEvent) {
    }
}
