package com.yuxi.projectdemo.wechat.service.impl;

import com.sun.tools.corba.se.idl.constExpr.Or;
import com.yuxi.projectdemo.wechat.dto.OrderDTO;
import com.yuxi.projectdemo.wechat.enums.ResultEnum;
import com.yuxi.projectdemo.wechat.exception.ProjectException;
import com.yuxi.projectdemo.wechat.service.BuyerService;
import com.yuxi.projectdemo.wechat.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOneOrder(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("[cancel order] cannot find order, orderId={}", orderId);
            throw new ProjectException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancelOrder(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("[find order]order's openid not the same, openid={}, orderDTO={}", openid, orderDTO);
            throw new ProjectException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
