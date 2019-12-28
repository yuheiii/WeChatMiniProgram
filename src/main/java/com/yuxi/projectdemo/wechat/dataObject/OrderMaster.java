package com.yuxi.projectdemo.wechat.dataObject;

import com.yuxi.projectdemo.wechat.enums.OrderStatusEnum;
import com.yuxi.projectdemo.wechat.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    /** default is new order - 0 */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** default is unpaid - 0 */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;

    //@Transient
    //This annotation is when there's no such a field in the database
    //private List<OrderDetail> orderDetailList;
    //Note: to make create order more convenient, then change it to a new DTO class
}
