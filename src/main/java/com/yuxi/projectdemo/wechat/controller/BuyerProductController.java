package com.yuxi.projectdemo.wechat.controller;

import com.yuxi.projectdemo.wechat.dataObject.ProductCategory;
import com.yuxi.projectdemo.wechat.dataObject.ProductInfo;
import com.yuxi.projectdemo.wechat.frontendObject.ProductFrontendObject;
import com.yuxi.projectdemo.wechat.frontendObject.ProductInfoFrontendObject;
import com.yuxi.projectdemo.wechat.frontendObject.ResultFrontendObject;
import com.yuxi.projectdemo.wechat.service.ProductCategoryService;
import com.yuxi.projectdemo.wechat.service.ProductInfoService;
import com.yuxi.projectdemo.wechat.utils.ResultFrontendObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultFrontendObject list() {

        /**The steps to show the data from db:
         * 1. find all products that are in stock
         * 2. find type (do the search operation once)
         * 3. combine the data
         */

        //1.
        List<ProductInfo> productInfoList = productInfoService.findInStockAll();

        //2.
        //method 1:
//        List<Integer> categoryTypeList = new ArrayList<>();
//        for (ProductInfo productInfo : productInfoList) {
//            categoryTypeList.add(productInfo.getCategoryType());
//
//        }
        //method2: java 8 lambda
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        //3.
        List<ProductFrontendObject> productFrontendObjectList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductFrontendObject productFrontendObject = new ProductFrontendObject();
            productFrontendObject.setCategoryType(productCategory.getCategoryType());
            productFrontendObject.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoFrontendObject> productInfoFrontendObjectList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoFrontendObject productInfoFrontendObject = new ProductInfoFrontendObject();
                    BeanUtils.copyProperties(productInfo, productInfoFrontendObject);
                    productInfoFrontendObjectList.add(productInfoFrontendObject);
                }
            }
            productFrontendObject.setProductInfoFrontendObjectList(productInfoFrontendObjectList);
            productFrontendObjectList.add(productFrontendObject);
        }

        //Note: don't put search operation in db into a for loop. time overhead

        return ResultFrontendObjectUtil.success(productFrontendObjectList);
    }

}
