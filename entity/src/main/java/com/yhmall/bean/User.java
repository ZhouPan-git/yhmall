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
 * @TableName user
 */
@TableName(value ="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 0
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;
    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;
    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;
    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;
    /**
     * 地址
     */
    @TableField(value = "address")
    private String address;
    /**
     * 头像
     */
    @TableField(value = "head")
    private String head;
    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;
    /**
     * 身份证号
     */
    @TableField(value = "identitynum")
    private String identitynum;
    /**
     * 性别
     */
    @TableField(value = "sex")
    private String sex;
    /**
     * 支付宝账号
     */
    @TableField(value = "zfbid")
    private String zfbid;


}