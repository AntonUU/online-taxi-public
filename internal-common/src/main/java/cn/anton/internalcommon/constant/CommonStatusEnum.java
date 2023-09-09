package cn.anton.internalcommon.constant;

import lombok.Getter;

/*
 * @author: Anton
 * @create_date: 2023/9/5 18:49
 */
public enum CommonStatusEnum {
	
	VERIFICATION_CODE_ERROR(1099, "验证码不正确！"),
	
	/**
	 * 成功
	 */
	SUCCESS(1, "success"),
	/**
	 * 失败
	 */
	FAIL(0, "fail"),
	
	
	// Token类提示
	TOKEN_ERROR(1199, "token error ....")
	;
	@Getter
	private int code;
	@Getter
	private String message;
	
	CommonStatusEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
