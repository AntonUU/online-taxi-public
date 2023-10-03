package cn.anton.apidriver.service.impl;

import cn.anton.apidriver.remote.ApiPassengerCodeClient;
import cn.anton.apidriver.remote.ServiceDriverUserClient;
import cn.anton.apidriver.service.DriverUserService;
import cn.anton.internalcommon.constant.CommonStatusEnum;
import cn.anton.internalcommon.dao.DriverPhoneExists;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.VerificationCodeDTO;
import cn.anton.internalcommon.response.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/2 12:15
 */
@Service
@Slf4j
public class DriverUserServiceImpl implements DriverUserService {
	
	@Resource
	private ServiceDriverUserClient serviceDriverUserClient;
	
	@Resource
	private ApiPassengerCodeClient apiPassengerCodeClient;
	
	@Override
	public ResponseResult verificationCode(String driverPhone) {
		
		// 检查司机是否存在
		ResponseResult<DriverPhoneExists> result = serviceDriverUserClient.checkDriverByDriverPhone(driverPhone);
		if (result.getData().getIfExists() == 1) {
			// 手机号已存在
			return ResponseResult.fail(CommonStatusEnum.USER_EXISTS.getCode(), CommonStatusEnum.USER_EXISTS.getMessage());
		}
		
		// 获取验证码
		VerificationCodeDTO dto = new VerificationCodeDTO();
		dto.setDriverPhone(driverPhone);
		
		return apiPassengerCodeClient.verificationCode(dto);
	}
	
	@Override
	public ResponseResult verificationCodeCheck(VerificationCodeDTO verificationCodeDTO) {
		ResponseResult<TokenResponse> result = apiPassengerCodeClient.verificationCodeCheck(verificationCodeDTO);
		TokenResponse data = result.getData();
		log.info("AccessTokenStr: {}", data.getAccessToken());
		log.info("RefreshTokenStr: {}", data.getRefreshToken());
		return result;
	}
}
