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
	 * 用户 或 司机已存在
	 */
	USER_EXISTS(1209, "用户已存在...."),
	
	/**
	 * 计价规则不存在
	 */
	PRICE_RULE_EMPTY(1300, "计价规则不存在....."),
	
	// Token类提示
	TOKEN_ERROR(1199, "token error ...."),
	
	
	/**
	 * 地图服务  标志
	 */
	MAP_DISTRICT_ERROR(1400, "地图请求错误...."),
	
	CAR_DRIVER_USER_NOT_FIND(1500, "找不到司机或用户 .... "),
	CAR_DRIVER_USER_EXISTS(1510, "重复绑定 .... ");
	
	@Getter
	private int code;
	@Getter
	private String message;
	
	CommonStatusEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
