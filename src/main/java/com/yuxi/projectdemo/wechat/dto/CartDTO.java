package com.yuxi.projectdemo.wechat.dto;

import lombok.Data;

@Data
public class CartDTO {

    private String productId;

    private Integer productQuatity;

    public CartDTO(String productId, Integer productQuatity) {
        this.productId = productId;
        this.productQuatity = productQuatity;
    }
}
