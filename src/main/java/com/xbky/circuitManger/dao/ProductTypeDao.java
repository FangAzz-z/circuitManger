package com.xbky.circuitManger.dao;

import com.xbky.circuitManger.entity.ProductType;
import com.xbky.circuitManger.utils.ObjectUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductTypeDao extends BaseDao {

    public List<Map<String,Object>> queryAll(){
        String sql = "select id,category,model,brand from CM_PRODUCT_TYPE";
        List<Map<String,Object>> result = super.queryForList(sql, null);
        return result;
    }

    public int deleteById(Long id) {
        return super.commonDeleteById("CM_PRODUCT_TYPE",id+"");
    }

    public int add(ProductType pt) {
        String sql = String.format("insert into CM_PRODUCT_TYPE(category,model,brand,create_time,update_time) values('%s','%s','%s',now(),now())"
        ,pt.getCategory(),pt.getModel(),pt.getBrand());
        return super.insert(sql, null);
    }

    public int modify(ProductType info){
        StringBuffer sql = new StringBuffer("update CM_PRODUCT_TYPE set update_time = now() ");
        if (ObjectUtil.isNotNull(info.getCategory())) {
            sql.append(String.format(",category = '%s'", info.getCategory()));
        }
        if (ObjectUtil.isNotNull(info.getModel())) {
            sql.append(String.format(",model = '%s'",info.getModel()));
        }
        if (ObjectUtil.isNotNull(info.getBrand())) {
            sql.append(String.format(",brand = '%s'", info.getBrand()));
        }
        sql.append(String.format(" where id = '%s'",info.getId()));
        return super.update(sql.toString(),null);
    }
    public List<Map<String,Object>> queryByExample(ProductType pt){
        StringBuffer sql = new StringBuffer("select id,category,model,brand from CM_PRODUCT_TYPE where 1=1 ");
        if(ObjectUtil.isNotNull(pt.getCategory())){
            sql.append(String.format(" and category like '%s'",pt.getCategory()+"%"));
        }
        if(ObjectUtil.isNotNull(pt.getModel())){
            sql.append(String.format(" and model like '%s'",pt.getModel()+"%"));
        }
        if(ObjectUtil.isNotNull(pt.getBrand())){
            sql.append(String.format(" and brand like '%s",pt.getBrand()+"%"));
        }
        return super.queryForList(sql.toString(), null);
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
