package com.xbky.circuitManger.dao;

import com.xbky.circuitManger.entity.ProductType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductTypeDao extends BaseDao {

    public List<Map<String,Object>> queryAll(){
        String sql = "select id,category,model,brand from CM_PRODECT_TYPE";
        List<Map<String,Object>> result = super.queryForList(sql, null);
        return result;
    }

    public int deleteById(Long id) {
        String sql = "delete from CM_PRODECT_TYPE where ID = ?";
        Object[] obj = new Object[]{id};
        return super.update(sql, obj);
    }

    public int add(ProductType pt) {
        String sql = "insert into CM_PRODECT_TYPE(category,model,brand) values(?,?,?)";
        Object[] obj = new Object[]{pt.getCategory(),pt.getModel(),pt.getBrand()};
        return super.insert(sql, obj);
    }

    public int modify(ProductType productType){
        return 0;
    }

    private List<ProductType> fillElement(List<Map<String,Object>> tempList){
        List<ProductType> result = new ArrayList<>();
        for (Map<String, Object> map : tempList) {
            ProductType pt = new ProductType();
            pt.setId((Long)map.get("id"));
            pt.setCategory((String)map.get("category"));
            pt.setModel((String)map.get("model"));
            pt.setBrand(((String)map.get("brand")));
            result.add(pt);
        }
        return result;
    }
}
