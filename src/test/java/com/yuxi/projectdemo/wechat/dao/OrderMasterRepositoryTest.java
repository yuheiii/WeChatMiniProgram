package com.yuxi.projectdemo.wechat.dao;

import com.yuxi.projectdemo.wechat.dataObject.OrderMaster;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID = "110110";

    @Test
    void saveTest() {

    }
    @Test
    void findByBuyerOpenid() throws Exception {

        Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID, PageRequest.of(0, 1));
        System.out.println(result.getTotalElements());
        //Note: even if when there's only 1 page, you searched for page 2, total is still
        //gonna be the #, but content will show 0, so it's better to getTotalElements
        Assert.assertNotEquals(0, result.getTotalElements());


    }
}