package com.yuxi.projectdemo.wechat.service;

import com.yuxi.projectdemo.wechat.dataObject.ProductInfo;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    ProductInfo findOne(String productId);

    /** find all products that is in stock*/
    List<ProductInfo> findInStockAll();

    /**for seller to find, and add page scroll functionality*/
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /** add inventory */

    /** delete inventory*/

}
