package com.xbky.circuitManger.dao;

import com.xbky.circuitManger.entity.ProductType;
import com.xbky.circuitManger.utils.ObjectUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        StringBuffer sql = new StringBuffer("select id,category,model,brand , create_time, update_time from CM_PRODUCT_TYPE where 1=1 ");
        if(ObjectUtil.isNotNull(pt.getCategory())){
            sql.append(String.format(" and category like '%s'",pt.getCategory()+"%"));
        }
        if(ObjectUtil.isNotNull(pt.getModel())){
            sql.append(String.format(" and model like '%s'",pt.getModel()+"%"));
        }
        if(ObjectUtil.isNotNull(pt.getBrand())){
            sql.append(String.format(" and brand like '%s'",pt.getBrand()+"%"));
        }
        sql.append(" order by update_time desc");
        return super.queryForList(sql.toString(), null);
    }

    public boolean isExistSome(ProductType pt){
        StringBuffer sql = new StringBuffer("select id,category,model,brand , create_time, update_time from CM_PRODUCT_TYPE where 1=1 ");
        if(ObjectUtil.isNotNull(pt.getCategory())){
            sql.append(String.format(" and category = '%s'",pt.getCategory()));
        }
        if(ObjectUtil.isNotNull(pt.getModel())){
            sql.append(String.format(" and model = '%s'",pt.getModel()));
        }
        if(ObjectUtil.isNotNull(pt.getBrand())){
            sql.append(String.format(" and brand = '%s'",pt.getBrand()));
        }
        sql.append(" order by update_time desc");
        Boolean result = ObjectUtil.isNotNull(super.queryForList(sql.toString(), null))?true:false;
        return result;
    }

    public Map<String,Object> queryByExample(ProductType pt,int pageNo,int pageSize){
        StringBuffer sql = new StringBuffer("select id,category,model,brand , create_time, update_time from CM_PRODUCT_TYPE where 1=1 ");
        if(ObjectUtil.isNotNull(pt.getCategory())){
            sql.append(String.format(" and LOWER(category) like '%s'","%"+pt.getCategory().toLowerCase()+"%"));
        }
        if(ObjectUtil.isNotNull(pt.getModel())){
            sql.append(String.format(" and LOWER(model) like '%s'","%"+pt.getModel().toLowerCase()+"%"));
        }
        if(ObjectUtil.isNotNull(pt.getBrand())){
            sql.append(String.format(" and LOWER(brand) like '%s'","%"+pt.getBrand().toLowerCase()+"%"));
        }
        int count =  super.queryForList(sql.toString(), null).size();
        int total =  (count  +  pageSize  - 1) / pageSize;
        sql.append(String.format(" order by update_time desc  limit %s,%s ",pageNo*pageSize,pageSize));
        List<Map<String,Object>> list =  super.queryForList(sql.toString(), null);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", list);
        map.put("total", total);
        return map;
    }

    public List<String> queryAllCategory(){
        StringBuffer sql = new StringBuffer("select distinct category from CM_PRODUCT_TYPE where 1=1 ");
        List<Map<String,Object>> list =  super.queryForList(sql.toString(), null);
        List<String> dataList = list.stream().map(t->ObjectUtil.getString(t.get("category"))).collect(Collectors.toList());
        return dataList;
    }

    public List<String> queryBrandByCategory(String category) {
        StringBuffer sql = new StringBuffer("select distinct brand from CM_PRODUCT_TYPE where 1=1 ");
        sql.append(String.format(" and category = '%s'",category));
        List<Map<String,Object>> list =  super.queryForList(sql.toString(), null);
        List<String> dataList = list.stream().map(t->ObjectUtil.getString(t.get("brand"))).collect(Collectors.toList());
        return dataList;
    }

    public List<String> queryModelByBrand(String category,String brand) {
        StringBuffer sql = new StringBuffer("select distinct model from CM_PRODUCT_TYPE where 1=1 ");
        sql.append(String.format(" and category = '%s'",category));
        sql.append(String.format(" and brand = '%s'",brand));
        List<Map<String,Object>> list =  super.queryForList(sql.toString(), null);
        List<String> dataList = list.stream().map(t->ObjectUtil.getString(t.get("model"))).collect(Collectors.toList());
        return dataList;
    }
}
