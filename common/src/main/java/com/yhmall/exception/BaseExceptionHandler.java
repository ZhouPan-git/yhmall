package com.yhmall.exception;

import com.yhmall.result.Result;
import com.yhmall.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.FileNotFoundException;

/**
 * @author Nick
 * @Classname BaseExceptionHandler
 * @Date 2023/09/14 19:24
 * @Description
 */
@Slf4j
@ResponseBody
@RestControllerAdvice
public class BaseExceptionHandler {

	/**
	 * BaseException 异常捕获处理
	 * @param ex 自定义BaseException异常类型
	 * @return Result
	 */
	@ExceptionHandler(BaseException.class)
	public Result<?> handleException(BaseException ex) {
		log.error("程序异常：" + ex.toString());
		return Result.fail(ex.getStatus(), ex.getMessage());
	}

	/**
	 * BaseException 异常捕获处理
	 * @param ex 自定义BaseException异常类型
	 * @return Result
	 */
	@ExceptionHandler(ValidateCodeException.class)
	@ResponseStatus
	public Result<?> handleValidateCodeException(ValidateCodeException  ex) {
		log.error("验证码错误：" + ex.getMessage());
		return Result.fail(ex.getStatus(), ex.getMessage());
	}


	/**
	 * FileNotFoundException,NoHandlerFoundException 异常捕获处理
	 * @param exception 自定义FileNotFoundException异常类型
	 * @return Result
	 */
	@ExceptionHandler({FileNotFoundException.class, NoHandlerFoundException.class})
	public Result<?> noFoundException(Exception exception) {
		log.error("程序异常==>errorCode:{}, exception:{}", HttpStatus.NOT_FOUND.value(), exception.getMessage());
		return Result.fail(HttpStatus.NOT_FOUND.value(), exception.getMessage());
	}


	/**
	 * NullPointerException 空指针异常捕获处理
	 * @param ex 自定义NullPointerException异常类型
	 * @return Result
	 */
	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Result<?> handleException(NullPointerException ex) {
		log.error("程序异常：{}" + ex.toString());
		return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	}

	/**
	 * 通用Exception异常捕获
	 * @param ex 自定义Exception异常类型
	 * @return Result
	 */
	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Result<?> handleException(Exception ex) {
		log.error("程序异常：" + ex.toString());
		String message = ex.getMessage();
		if (StringUtils.contains(message, "Bad credentials")){
			message = "您输入的密码不正确";
		} else if (StringUtils.contains(ex.toString(), "InternalAuthenticationServiceException")) {
			message = "您输入的用户名不存在";
		}
		return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
	}


}