package com.yuxi.projectdemo.wechat.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10, "product does not exist"),

    PRODUCT_STOCK_ERROR(11, "incorrect inventory"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
