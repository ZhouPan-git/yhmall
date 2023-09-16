package com.yhmall.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName product
 */
@TableName(value ="product")
@Data
public class Product implements Serializable {
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
    private Integer categoryid;

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

    /**
     * 商品图片
     */
    @TableField(value = "img")
    private String img;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;



}