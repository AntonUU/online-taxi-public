package cn.anton.apipassenger.service;

import cn.anton.internalcommon.dao.ResponseResult;

/*
 * @author: Anton
 * @create_date: 2023/9/5 15:00
 */
public interface VerificationCodeService {

	void generateCode(String passengerPhon);
	
	ResponseResult checkCode(String passengerPhone, String verificationCode);
	
}
