package com.yuxi.projectdemo.wechat.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {

    INSTOCK(0, "in stock"),
    OUTOFSTOCK(1, "out of stock")
    ;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
