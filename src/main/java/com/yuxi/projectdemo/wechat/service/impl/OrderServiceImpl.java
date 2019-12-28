package com.yuxi.projectdemo.wechat.service.impl;

import com.yuxi.projectdemo.wechat.dao.OrderDetailRepository;
import com.yuxi.projectdemo.wechat.dao.OrderMasterRepository;
import com.yuxi.projectdemo.wechat.dataObject.OrderDetail;
import com.yuxi.projectdemo.wechat.dataObject.OrderMaster;
import com.yuxi.projectdemo.wechat.dataObject.ProductInfo;
import com.yuxi.projectdemo.wechat.dto.CartDTO;
import com.yuxi.projectdemo.wechat.dto.OrderDTO;
import com.yuxi.projectdemo.wechat.enums.ResultEnum;
import com.yuxi.projectdemo.wechat.exception.ProjectException;
import com.yuxi.projectdemo.wechat.service.OrderService;
import com.yuxi.projectdemo.wechat.service.ProductInfoService;
import com.yuxi.projectdemo.wechat.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;


    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        /**
         * 1. find product - amount, price
         * 2. calculate total price for the order
         * 3. insert into order db - orderMaster and orderDetail
         * 4. deduct inventory
         */

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
//        List<CartDTO> cartDTOList = new ArrayList<>();
        //1.
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new ProjectException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2.
            orderAmount = orderDetail.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //3. orderDetail db insertion
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
//          CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
//          cartDTOList.add(cartDTO);
        }

        //3. orderMaster
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMasterRepository.save(orderMaster);

        //4.
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());

        productInfoService.decreaseInventory(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancelOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO completeOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO payOrder(OrderDTO orderDTO) {
        return null;
    }
}
