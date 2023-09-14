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

/**
 * 
 * @TableName order_product
 */
@TableName(value ="order_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 订单id
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer order_id;
    /**
     * 商品id
     */
    @TableField(value = "product_id")
    private Integer product_id;
    /**
     * 数量
     */
    @TableField(value = "count")
    private Integer count;
    /**
     * 单价
     */
    @TableField(value = "price")
    private BigDecimal price;


}