package com.yhmall.result;

/**
 * @author Nick
 * @Classname IResultCode
 * @Date 2023/09/14 19:18
 * @Description 统一返回结果接口
 */
public interface IResultCode {
	/**
	 * 返回码
	 *
	 * @return int
	 */
	int getCode();

	/**
	 * 返回消息
	 *
	 * @return String
	 */
	String getMsg();
}
