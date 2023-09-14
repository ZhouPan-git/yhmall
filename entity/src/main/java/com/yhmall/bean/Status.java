package com.yhmall.bean;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author Nick
 * @Classname Status
 * @Date 2023/09/14 15:29
 * @Description
 */
public enum Status {
	Success("成功",1),
	Fail("失败",0);

	private String key;
	//标记数据库存的值
	@EnumValue
	private Integer value;

	Status(String key, Integer value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
