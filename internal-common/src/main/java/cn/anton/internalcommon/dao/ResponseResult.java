package cn.anton.internalcommon.dao;

import cn.anton.internalcommon.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/*
 * @author: Anton
 * @create_date: 2023/9/5 18:52
 */
@Data
@Accessors(chain = true)
public class ResponseResult<T> {

	private int code;
	private String message;
	private T data;
	
	/**
	 * 成功响应的方法
	 * @return
	 */
	public static <T> ResponseResult success(T data){
		return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).
				setMessage(CommonStatusEnum.SUCCESS.getMessage()).
				setData(data);
	}
	
	public static ResponseResult success(){
		return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).
				       setMessage(CommonStatusEnum.SUCCESS.getMessage());
	}
	
	/**
	 * 自定义失败错误码和提示信息
	 * @param code
	 * @param message
	 * @return
	 */
	public static ResponseResult fail(int code, String message){
		return new ResponseResult().setCode(code).setMessage(message);
	}
	
	/**
	 * 统一的失败
	 * @param data
	 * @return
	 * @param <T>
	 */
	public static <T> ResponseResult fila(T data){
		return new ResponseResult().setData(data);
	}
	
	/**
	 * 失败： 自定义失败， 错误码和提示信息
	 * @param code
	 * @param message
	 * @param data
	 * @return
	 */
	public static ResponseResult fail(int code, String message, String data){
		return new ResponseResult().setCode(code).setMessage(message).setData(data);
	}
	

}
