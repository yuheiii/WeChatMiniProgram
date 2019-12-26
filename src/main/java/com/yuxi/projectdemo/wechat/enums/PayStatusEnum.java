package com.yuxi.projectdemo.wechat.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {
    WAIT(0, "unpaid"),
    SUCCESS(1, "paid successfully")
    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

