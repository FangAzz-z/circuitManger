package com.xbky.circuitManger.dao;

import com.xbky.circuitManger.entity.ProductType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductTypeDao extends BaseDao {

    public List<ProductType> queryAll(){
        String sql = "select category,model,brand from cm_prodect_type";
        List<Map<String,Object>> result = super.queryForList(sql, null);
        return fillElement(result);
    }

    private List<ProductType> fillElement(List<Map<String,Object>> tempList){
        List<ProductType> result = new ArrayList<>();
        for (Map<String, Object> map : tempList) {
            ProductType pt = new ProductType();
            pt.setCategory((String)map.get("category"));
            pt.setModel((String)map.get("model"));
            pt.setBrand(((String)map.get("brand")));
            result.add(pt);
        }
        return result;
    }
}
