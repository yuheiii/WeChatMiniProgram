package com.yuxi.projectdemo.wechat.exception;

import com.yuxi.projectdemo.wechat.enums.ResultEnum;

public class ProjectException extends RuntimeException {

    private Integer code;

    public ProjectException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        //Note: RuntimeException has message field, so we pass the enum message to super constructor
        this.code = resultEnum.getCode();
    }

    public ProjectException(Integer code, String messgae) {
        super(messgae);
        this.code = code;
    }
}
