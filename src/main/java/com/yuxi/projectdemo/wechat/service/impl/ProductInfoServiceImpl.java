package com.yuxi.projectdemo.wechat.service.impl;

import com.yuxi.projectdemo.wechat.dao.ProductInfoRepository;
import com.yuxi.projectdemo.wechat.dataObject.ProductInfo;
import com.yuxi.projectdemo.wechat.dto.CartDTO;
import com.yuxi.projectdemo.wechat.enums.ProductStatusEnum;
import com.yuxi.projectdemo.wechat.enums.ResultEnum;
import com.yuxi.projectdemo.wechat.exception.ProjectException;
import com.yuxi.projectdemo.wechat.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public void increaseInventory(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).orElse(null);
            if (productInfo == null) {
                throw new ProjectException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuatity();
            productInfo.setProductStock(result);

            repository.save(productInfo);
        }

    }

    @Override
    @Transactional
    public void decreaseInventory(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).orElse(null);
            if (productInfo == null) {
                throw new ProjectException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuatity();
            if (result < 0) {
                throw new ProjectException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);

            repository.save(productInfo);
        }
    }
}
