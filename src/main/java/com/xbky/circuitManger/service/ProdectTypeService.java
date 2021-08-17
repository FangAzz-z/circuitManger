package com.xbky.circuitManger.service;


import com.xbky.circuitManger.dao.ProductTypeDao;
import com.xbky.circuitManger.entity.ProductType;

import java.util.List;

public class ProdectTypeService {
    ProductTypeDao dao = new ProductTypeDao();

    public List<ProductType> queryAll(){
        return dao.queryAll();
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int add(ProductType productType) {
        return dao.add(productType);
    }
}
