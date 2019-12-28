package com.yuxi.projectdemo.wechat.dao;

import com.yuxi.projectdemo.wechat.dataObject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    //think about methods ourselves
    //find products that is in stock
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
