package com.yuxi.projectdemo.wechat.frontendObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/** the inner object that http request returns
 * includes type
 */

@Data
public class ProductFrontendObject {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoFrontendObject> productInfoFrontendObjectList;
}
