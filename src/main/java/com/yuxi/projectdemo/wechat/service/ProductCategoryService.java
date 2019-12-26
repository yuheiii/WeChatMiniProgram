package com.yuxi.projectdemo.wechat.service;

import com.yuxi.projectdemo.wechat.dataObject.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    //buyers don't need the first two methods
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);


}
