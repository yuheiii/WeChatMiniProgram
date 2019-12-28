package com.yuxi.projectdemo.wechat.service;

import com.yuxi.projectdemo.wechat.dataObject.OrderMaster;
import com.yuxi.projectdemo.wechat.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    /** create an order */
    OrderDTO createOrder(OrderDTO orderDTO);

    /** find single order */
    OrderDTO findOne(String orderId);

    /** find order list */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /** cancel an order */
    OrderDTO cancelOrder(OrderDTO orderDTO);

    /** mark an order as complete */
    OrderDTO completeOrder(OrderDTO orderDTO);

    /** pay for an order */
    OrderDTO payOrder(OrderDTO orderDTO);
}
