package cn.anton.apidriver.service;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.VerificationCodeDTO;

/*
 * @author: Anton
 * @create_date: 2023/10/2 12:14
 */
public interface DriverUserService {
	
	ResponseResult verificationCode(String driverPhone);
	
	ResponseResult verificationCodeCheck(VerificationCodeDTO verificationCodeDTO);
}
