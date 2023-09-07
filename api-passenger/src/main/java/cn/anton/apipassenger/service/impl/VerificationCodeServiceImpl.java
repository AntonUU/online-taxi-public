package cn.anton.apipassenger.service.impl;

import cn.anton.apipassenger.feign.ServicePassengerUserClient;
import cn.anton.apipassenger.feign.ServiceVerificationcodeCilent;
import cn.anton.apipassenger.service.VerificationCodeService;
import cn.anton.internalcommon.constant.CommonStatusEnum;
import cn.anton.internalcommon.constant.IdentityConstant;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.VerificationCodeDTO;
import cn.anton.internalcommon.response.TokenResponse;
import cn.anton.internalcommon.util.JWTUtils;
import cn.anton.internalcommon.util.PrefixGeneratorUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/*
 * @author: Anton
 * @create_date: 2023/9/5 14:57
 */
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
	
	@Resource
	private ServiceVerificationcodeCilent serviceVerificationcodeCilent;
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	@Resource
	private ServicePassengerUserClient servicePassengerUserClient;
	
	private final String VERIFICATIONCODEPREFIX = "passenger-verification-code-";
	
	
	
	@Override
	public void generateCode(String passengerPhone) {
		
		// 调用电信API， 获取验证码
		ResponseResult responseResult = serviceVerificationcodeCilent.numberCode();
		Integer numberCode = (Integer) responseResult.getData();
		
		// 存入Redis
		String key = generatorKeyByPhone(passengerPhone);
		String val = numberCode + "";
		stringRedisTemplate.opsForValue().set(key, val, 2, TimeUnit.MINUTES);
		
	}
	
	/**
	 * 验证乘客验证码并颁发token
	 * @param passengerPhone
	 * @param verificationCode
	 * @return
	 */
	@Override
	public ResponseResult checkCode(String passengerPhone, String verificationCode) {
		// 根据手机号，去redis读取验证码
		String key = generatorKeyByPhone(passengerPhone);
		String codeRedis = stringRedisTemplate.opsForValue().get(key);
		
		// 校验验证码
		System.out.println("redis中的code: " + codeRedis);
		if (codeRedis != null && codeRedis.equals(verificationCode)) {
			VerificationCodeDTO dto = new VerificationCodeDTO();
			dto.setPassengerPhone(passengerPhone);
			// 判断用户是否有过注册
			ResponseResult responseResult = servicePassengerUserClient.loginOrRegister(dto);
			// 删除redis中的验证码
			stringRedisTemplate.delete(generatorKeyByPhone(passengerPhone));
			// 颁发令牌
			String token = JWTUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_DIENTITY);
			System.out.println("获得的token: "+ token);
			
			// 将令牌放入Redis
			stringRedisTemplate.opsForValue()
					.set(PrefixGeneratorUtils.generatorTokenKey(passengerPhone, IdentityConstant.PASSENGER_DIENTITY), token, 30, TimeUnit.DAYS);
			
			// 响应
			TokenResponse tokenResponse = new TokenResponse();
			tokenResponse.setToken(token);
			return ResponseResult.success(tokenResponse);
		} else {
			// 验证码不正确
			return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getMessage());
		}
		
	}
	
	/**
	 * 根据手机号生成key
	 * @param passengerPhone
	 * @return
	 */
	private String generatorKeyByPhone(String passengerPhone){
		return VERIFICATIONCODEPREFIX + passengerPhone;
	}
}
