package com.yhmall.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName cart
 */
@TableName(value ="cart")
@Data
public class Cart implements Serializable {
    /**
     * 购物车id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Integer user_id;

    /**
     * 商品id
     */
    @TableField(value = "product_id")
    private Integer product_id;

    /**
     * 购物项数量
     */
    @TableField(value = "quantity")
    private Integer quantity;

    /**
     * 购物车创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime create_time;

    /**
     * 购物车更新时间
     */
    @TableField(value = "update_time")
    private LocalDateTime update_time;

    /**
     * 商品名字
     */
    @TableField(value = "product_name")
    private String product_name;

    /**
     * 商品图片
     */
    @TableField(value = "product_img")
    private String product_img;

    /**
     * 购物项价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}