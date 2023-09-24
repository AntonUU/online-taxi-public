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
	
	// User类提示  1200~1299
	USER_NOT_EXISTS(1200, "用户不存在 ... "),
	
	/**
	 * 计价规则不存在
	 */
	PRICE_RULE_EMPTY(1300, "计价规则不存在....."),
	
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
