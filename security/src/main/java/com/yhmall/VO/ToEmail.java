package com.yhmall.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor //生成一个包含所有类字段的构造函数
public class ToEmail implements Serializable {
    /**
     *  邮件接受方
     */
    private String tos;
    /**
     *      邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;
}
