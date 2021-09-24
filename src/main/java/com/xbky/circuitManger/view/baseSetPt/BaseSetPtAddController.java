package com.xbky.circuitManger.view.baseSetPt;

import com.xbky.circuitManger.Main;
import com.xbky.circuitManger.dao.ProductTypeDao;
import com.xbky.circuitManger.entity.ProductType;
import com.xbky.circuitManger.utils.ObjectUtil;
import com.xbky.circuitManger.view.common.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

public class BaseSetPtAddController implements Initializable {

    private static Logger log = LoggerFactory.getLogger(BaseSetPtAddController.class);

    @FXML
    public TextField tfId;
    @FXML
    public TextField tfCategory;
    @FXML
    public TextField tfModel;
    @FXML
    public TextField tfBrand;


    private  static Stage dialog = null;
    private  static Runnable resultHandld = null;

    private int mark;

    ProductTypeDao prodectTypeService = new ProductTypeDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void submitData(ActionEvent actionEvent) throws IOException {
        ProductType pt = new  ProductType();
        if(ObjectUtil.hasNull(tfCategory.getText(),tfBrand.getText(),tfModel.getText())){
            nullWarn();
            return;
        };
        pt.setCategory(tfCategory.getText());
        pt.setBrand(tfBrand.getText());
        pt.setModel(tfModel.getText());
        //相同数据校验
        if(prodectTypeService.isExistSome(pt)){
            StageManager.nullWarn("已存在相同的数据！");
            return;
        }
        if(ObjectUtil.isNull(tfId.getText())) {
            prodectTypeService.add(pt);
        }else{
            pt.setId(ObjectUtil.getLong(tfId.getText()));
            prodectTypeService.modify(pt);
        }
         if(dialog!=null) {
             dialog.close();
         }
        if (resultHandld != null) {
            resultHandld.run();
        }
    }

    public void cancelSubmit(ActionEvent actionEvent) {
        if(dialog!=null) {
            dialog.close();
        }
    }

    public void nullWarn() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("提示");
        alert.setContentText("输入值不能为空");
        alert.setHeaderText("");
        alert.initOwner( dialog);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == javafx.scene.control.ButtonType.OK){
            alert.close();
        }
    }

    public  Stage getDialog() {
        return dialog;
    }

    public void setDialog(Stage dialog) {
        BaseSetPtAddController.dialog = dialog;
    }

    public  Runnable getResultHandld() {
        return resultHandld;
    }

    public  void setResultHandld(Runnable resultHandld) {
        BaseSetPtAddController.resultHandld = resultHandld;
    }

    public void setBaseData(ProductType productType){
        tfCategory.setText(productType.getCategory());
        tfModel.setText(productType.getModel());
        tfBrand.setText(productType.getBrand());
        tfId.setText(productType.getId()+"");
    }


}
