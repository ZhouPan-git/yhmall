package com.yhmall.exception;

import com.yhmall.result.ResultCode;
import lombok.Data;

/**
 * @author Nick
 * @Classname ValidateCodeException
 * @Date 2023/09/14 19:23
 * @Description
 */
@Data
public class ValidateCodeException extends RuntimeException {

	private static final long serialVersionUID = -7285211528095468156L;
	private int status = ResultCode.ERROR.getCode();

	public ValidateCodeException() {
	}

	public ValidateCodeException(String msg) {
		super(msg);
	}

	public ValidateCodeException(int code, String message) {
		super(message);
		this.status = code;
	}
}