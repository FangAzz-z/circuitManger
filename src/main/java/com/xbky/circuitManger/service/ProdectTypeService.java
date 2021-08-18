package com.xbky.circuitManger.service;


import com.xbky.circuitManger.dao.ProductTypeDao;
import com.xbky.circuitManger.entity.ProductType;

import java.util.List;
import java.util.Map;

public class ProdectTypeService {
    ProductTypeDao dao = new ProductTypeDao();

    public List<Map<String,Object>> queryAll(){
        return dao.queryAll();
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int add(ProductType productType) {
        return dao.add(productType);
    }
}
