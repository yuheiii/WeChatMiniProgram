package com.yuxi.projectdemo.wechat.service;

import com.yuxi.projectdemo.wechat.dto.OrderDTO;

public interface BuyerService {

    OrderDTO findOneOrder(String openid, String orderId);

    OrderDTO cancelOrder(String openid, String orderId);
}
