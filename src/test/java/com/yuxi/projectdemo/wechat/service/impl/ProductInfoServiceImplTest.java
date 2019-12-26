package com.yuxi.projectdemo.wechat.service.impl;

import com.yuxi.projectdemo.wechat.dataObject.ProductInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.rmi.server.ExportException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest
class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    void findOne() throws Exception{
        ProductInfo productInfo = productInfoService.findOne("123456");
        Assert.assertEquals("123456", productInfo.getProductId());
    }

    @Test
    void findInStockAll() throws Exception{
        List<ProductInfo> productInfoList = productInfoService.findInStockAll();
        Assert.assertNotEquals(0, productInfoList.size());
    }

    @Test
    /**PageRequest implements Pageable*/
    void findAll() throws Exception {
        Page<ProductInfo> productInfoPage = productInfoService.findAll(PageRequest.of(0, 2));
        //System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotEquals(0, productInfoPage.getTotalElements());
    }

    @Test
    void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        /**set other fields */
        ProductInfo result = productInfoService.save(productInfo);
        Assert.assertNotNull(result);
    }

}