package com.yuxi.projectdemo.wechat.service.impl;

import com.yuxi.projectdemo.wechat.dataObject.OrderDetail;
import com.yuxi.projectdemo.wechat.dto.OrderDTO;
import com.yuxi.projectdemo.wechat.enums.OrderStatusEnum;
import com.yuxi.projectdemo.wechat.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "110110";

    private final String ORDER_ID = "1497183332311989948";

    @Test
    void createOrder() throws Exception{

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("Buyer1");
        orderDTO.setBuyerAddress("Address1");
        orderDTO.setBuyerPhone("1234353628");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1234568");
        o1.setProductQuantity(1);
        orderDetailList.add(o1);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.createOrder(orderDTO);
        log.info("[create order] result={}", result);
        Assert.assertNotNull(result);
    }

    @Test
    void findOne() throws Exception{
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        log.info("[find single order] result={}", orderDTO);
        Assert.assertEquals(ORDER_ID, orderDTO.getOrderId());

    }

    @Test
    void findList() throws Exception{
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, PageRequest.of(0, 2));
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    void cancelOrder() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.cancelOrder(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    void completeOrder() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.completeOrder(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    void payOrder() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.payOrder(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }
}