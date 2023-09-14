package com.yhmall.exception;

import com.yhmall.result.ResultCode;
import lombok.Data;

/**
 * @author Nick
 * @Classname BaseException
 * @Date 2023/09/14 19:22
 * @Description
 */
@Data
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 5782968730281544562L;

	private int status = ResultCode.ERROR.getCode();

	public BaseException() {
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(int status, String message) {
		super(message);
		this.status = status;
	}
}