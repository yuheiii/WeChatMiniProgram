package com.yuxi.projectdemo.wechat.dto;

import com.yuxi.projectdemo.wechat.dataObject.OrderDetail;
import com.yuxi.projectdemo.wechat.enums.OrderStatusEnum;
import com.yuxi.projectdemo.wechat.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

//DTO package is for data transfer within layers
@Data
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

    private Date createTime;

    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
