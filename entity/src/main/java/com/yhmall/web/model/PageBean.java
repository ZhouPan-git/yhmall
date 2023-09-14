package com.yhmall.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Nick
 * @Classname PageBean
 * @Date 2023/09/14 9:55
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean<T> {
	//界面给的已知参数
	//当前第几页
	private int pageno=1;
	//每页多少条
	private int pagesize=5;
	//排序列列名
	private String sortby;
	//排序的值 asc or desc
	private String sort;

	//数据库查询得到
	//总记录数
	private long total;
	//存数据的数据集
	private List<T> dataset;

	//业务层中计算
	//总共多少页
	private int totalpages;
	//上一页页数
	private int pre;
	//下一页页数
	private int next;
}

