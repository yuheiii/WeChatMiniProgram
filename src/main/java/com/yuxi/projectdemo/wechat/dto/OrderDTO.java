package com.yuxi.projectdemo.wechat.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yuxi.projectdemo.wechat.dataObject.OrderDetail;
import com.yuxi.projectdemo.wechat.enums.OrderStatusEnum;
import com.yuxi.projectdemo.wechat.enums.PayStatusEnum;
import com.yuxi.projectdemo.wechat.utils.serializer.DateToLongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//DTO package is for data transfer within layers
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
//e.g.: when OrderDetailList is null, don't return that value
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    /** default is new order - 0 */
    private Integer orderStatus;

    /** default is unpaid - 0 */
    private Integer payStatus;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;/** = new ArrayList<>()*/
}
