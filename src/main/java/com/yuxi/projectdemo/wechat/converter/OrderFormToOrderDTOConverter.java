package com.yuxi.projectdemo.wechat.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuxi.projectdemo.wechat.dataObject.OrderDetail;
import com.yuxi.projectdemo.wechat.dto.OrderDTO;
import com.yuxi.projectdemo.wechat.enums.ResultEnum;
import com.yuxi.projectdemo.wechat.exception.ProjectException;
import com.yuxi.projectdemo.wechat.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderFormToOrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        //cannot use BeanUtils.copyProperties, because the field names are not the same

        //json to list
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());

        } catch (Exception e) {
            log.error("[object convert]error, string={}", orderForm.getItems());
            throw new ProjectException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;

    }
}
