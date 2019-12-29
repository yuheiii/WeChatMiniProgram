package com.yuxi.projectdemo.wechat.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

//form package is for 表单验证

@Data
public class OrderForm {

    @NotEmpty(message = "name is mandatory")
    private String name;

    @NotEmpty(message = "phone number is mandatory")
    private String phone;

    @NotEmpty(message = "address is mandatory")
    private String address;

    @NotEmpty(message = "openid is mandatory")
    private String openid;

    @NotEmpty(message = "cart cannot be empty")
    private String items;

}
