package com.yuxi.projectdemo.wechat.service.impl;

import com.yuxi.projectdemo.wechat.DAO.ProductInfoRepository;
import com.yuxi.projectdemo.wechat.dataObject.ProductInfo;
import com.yuxi.projectdemo.wechat.enums.ProductStatusEnum;
import com.yuxi.projectdemo.wechat.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findById(productId).orElse(null);
    }

    @Override
    public List<ProductInfo> findInStockAll() {
        return repository.findByProductStatus(ProductStatusEnum.INSTOCK.getCode());
    }

    @Override
    /** Note: return Page object*/
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }
}
