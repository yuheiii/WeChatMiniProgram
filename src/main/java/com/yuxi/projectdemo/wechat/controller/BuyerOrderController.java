package com.yuxi.projectdemo.wechat.controller;

import com.yuxi.projectdemo.wechat.converter.OrderFormToOrderDTOConverter;
import com.yuxi.projectdemo.wechat.dto.OrderDTO;
import com.yuxi.projectdemo.wechat.enums.ResultEnum;
import com.yuxi.projectdemo.wechat.exception.ProjectException;
import com.yuxi.projectdemo.wechat.form.OrderForm;
import com.yuxi.projectdemo.wechat.frontendObject.ResultFrontendObject;
import com.yuxi.projectdemo.wechat.service.BuyerService;
import com.yuxi.projectdemo.wechat.service.OrderService;
import com.yuxi.projectdemo.wechat.utils.ResultFrontendObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //create order
    /**
     *
     * POST /wechat/buyer/order/create
     *
     * @param
     *
     * //name: "xxx"
     * phone: "1231231"
     * address: "xxxx"
     * openid: "xxxxx"
     * items: [{
     *     productId: "xxxx",
     *     productOuantity" 2
     * }]
     *
     * @returns
     *
     * {
     *     "code": 0,
     *     "msg": "success".
     *     "data": {
     *         "orderId": "xxxxx"
     *     }
     * }
     *
     */
    @PostMapping("/create")
    public ResultFrontendObject<Map<String, String>> create(@Valid OrderForm orderForm,
                                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[create order] params wrong, orderForm={}", orderForm);
            throw new ProjectException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderFormToOrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[create order]cart cannot be empty");
            throw new ProjectException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.createOrder(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultFrontendObjectUtil.success(map);
    }


    //order list
    /**
     * GET /wechat/buyer/order/list
     *
     * @param
     *
     * openid: xxxx
     * page: 0
     * size: 10
     *
     */
    @GetMapping("/list")
    public ResultFrontendObject<List<OrderDTO>> list (@RequestParam("openid") String openid,
                                                      @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("[order list]openid is empty");
            throw new ProjectException(ResultEnum.PARAM_ERROR);
        }

        Page<OrderDTO> orderDTOPage = orderService.findList(openid, PageRequest.of(page, size));
        return ResultFrontendObjectUtil.success(orderDTOPage.getContent());
    }


    //order detail

    /**
     * GET /wechat/buyer/order/detail
     *
     * @param openid: orderId:
     */

    @GetMapping("/detail")
    public ResultFrontendObject<OrderDTO> detail(@RequestParam("openid") String openid,
                                                 @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.findOneOrder(openid, orderId);
        return ResultFrontendObjectUtil.success(orderDTO);
    }


    //cancel order

    @PostMapping("/cancel")
    /**
     *
     * POST /wechat/buyer/order/cancel
     */
    public ResultFrontendObject cancel(@RequestParam("openid") String openid,
                                       @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(openid, orderId);
        return ResultFrontendObjectUtil.success();

    }
}

