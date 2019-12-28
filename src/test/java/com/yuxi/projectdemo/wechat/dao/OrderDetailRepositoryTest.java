package com.yuxi.projectdemo.wechat.dao;

import com.yuxi.projectdemo.wechat.dataObject.OrderDetail;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    void saveTest() {

    }
    @Test
    void findByOrderId() throws Exception{
        List<OrderDetail> orderDetailList = repository.findByOrderId("12345");
        Assert.assertNotEquals(0, orderDetailList.size());
    }
}