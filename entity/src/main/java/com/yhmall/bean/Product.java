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
 * @TableName product
 */
@TableName(value ="product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 商品id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 商品名称
     */
    @TableField(value = "name")
    private String name;
    /**
     * 分类id
     */
    @TableField(value = "category_id")
    private Integer category_id;
    /**
     * 商品描述
     */
    @TableField(value = "description")
    private String description;
    /**
     * 价格
     */
    @TableField(value = "price")
    private BigDecimal price;
    /**
     * 库存数量
     */
    @TableField(value = "stock")
    private Integer stock;
    /**
     * 状态(1正常 0下架)
     */
    @TableField(value = "status")
    private Integer status;
    /**
     * 销量
     */
    @TableField(value = "salecount")
    private String salecount;
    /**
     * 发货地址
     */
    @TableField(value = "deliveryaddr")
    private String deliveryaddr;
    /**
     * 特价商品
     */
    @TableField(value = "special")
    private String special;


}