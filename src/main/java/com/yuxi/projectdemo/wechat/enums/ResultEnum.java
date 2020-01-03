package com.yuxi.projectdemo.wechat.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    PARAM_ERROR(1, "param wrong"),

    PRODUCT_NOT_EXIST(10, "product does not exist"),

    PRODUCT_STOCK_ERROR(11, "incorrect inventory"),

    ORDER_NOT_EXIST(12, "order does not exist"),

    ORDERDETAIL_NOT_EXIST(13, "order detail does not exist"),

    ORDER_STATUS_ERROR(14, "order status error"),

    ORDER_UPDATE_ERROR(15, "order update failure"),

    ORDER_DETAIL_EMPTY(16, "order detail is empty"),

    ORDER_PAY_STATUS_ERROR(17, "order pay status wrong"),

    CART_EMPTY(18, "caet is empty"),

    ORDER_OWNER_ERROR(19, "this order doesn't belong to this user"),

    WX_MP_ERROR(20, "wechat mp error"),

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
