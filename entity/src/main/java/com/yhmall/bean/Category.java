package com.yhmall.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 * @TableName category
 */
@TableName(value ="category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 分类id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 分类名称
     */
    @TableField(value = "name")
    private String name;
    /**
     * 状态(1:正常,0:删除)
     */
    @TableField(value = "status")
    private Integer status;
    @TableField(value = "parent_id")
    private Integer parent_id;


}