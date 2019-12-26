package com.yuxi.projectdemo.wechat.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    NEW(0, "new order"),
    FINISHED(1, "order is completed"),
    CANCEL(2, "order is cancelled")
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
