package com.yhmall.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 
 * @TableName payment
 */
@TableName(value ="payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 支付id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 订单id
     */
    @TableField(value = "order_id")
    private Integer order_id;
    /**
     * 支付金额
     */
    @TableField(value = "amount")
    private BigDecimal amount;
    /**
     * 支付类型
     */
    @TableField(value = "type")
    private Integer type;
    /**
     * 支付状态
     */
    @TableField(value = "status")
    private Integer status;
    /**
     * 交易流水号
     */
    @TableField(value = "transaction_id")
    private String transaction_id;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime create_time;


}