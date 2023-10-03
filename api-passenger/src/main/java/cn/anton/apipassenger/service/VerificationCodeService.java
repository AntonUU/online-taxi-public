package cn.anton.apipassenger.service;

import cn.anton.internalcommon.dao.ResponseResult;

/*
 * @author: Anton
 * @create_date: 2023/9/5 15:00
 */
public interface VerificationCodeService {
	
	/**
	 * 生成验证码
	 * @param passengerPhone
	 * @param flag
	 */
	void generateCode(String passengerPhone,boolean flag);
	
	/**
	 * 验证验证码
	 * @param passengerPhone 乘客或司机手机号
	 * @param verificationCode 用户填写的验证码
	 * @param flag 0 司机 1 乘客
	 * @return
	 */
	ResponseResult checkCode(String passengerPhone, String verificationCode, boolean flag);
	
}
