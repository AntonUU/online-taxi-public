package cn.anton.apipassenger.service.impl;

import cn.anton.apipassenger.feign.ServiceDriverUserClient;
import cn.anton.apipassenger.feign.ServicePassengerUserClient;
import cn.anton.apipassenger.feign.ServiceVerificationcodeCilent;
import cn.anton.apipassenger.service.VerificationCodeService;
import cn.anton.internalcommon.constant.CommonStatusEnum;
import cn.anton.internalcommon.constant.DriverCarStatusConstant;
import cn.anton.internalcommon.constant.IdentityConstant;
import cn.anton.internalcommon.constant.TokenConstant;
import cn.anton.internalcommon.dao.DriverPhoneExists;
import cn.anton.internalcommon.dao.DriverUser;
import cn.anton.internalcommon.dao.DriverUserWorkStatus;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.VerificationCodeDTO;
import cn.anton.internalcommon.response.TokenResponse;
import cn.anton.internalcommon.util.JWTUtils;
import cn.anton.internalcommon.util.PrefixGeneratorUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
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
	@Resource
	private ServiceDriverUserClient serviceDriverUserClient;
	@Resource
	private Executor executor;
	
	private final static String VERIFICATIONCODEPREFIX = "passenger-verification-code-";
	
	private final static String DRIVER = "driver";
	private final static String PASSENGER = "passenger";
	
	
	/**
	 * 验证码的生成
	 *
	 * @param passengerPhone 司机或乘客手机号
	 * @param flag           0 司机 1 乘客
	 */
	@Override
	public void generateCode(String passengerPhone, boolean flag) {
		
		// 调用电信API， 获取验证码
		ResponseResult responseResult = serviceVerificationcodeCilent.numberCode();
		Integer numberCode = (Integer) responseResult.getData();
		
		// 存入Redis
		String key = generatorKeyByPhone(passengerPhone, flag);
		String val = numberCode + "";
		stringRedisTemplate.opsForValue().set(key, val, 2, TimeUnit.MINUTES);
		
	}
	
	
	/**
	 * 验证乘客验证码并颁发token
	 *
	 * @param passengerPhone
	 * @param verificationCode
	 * @return
	 */
	@Override
	public ResponseResult checkCode(String passengerPhone, String verificationCode, boolean flag) {
		// 根据手机号，去redis读取验证码
		String key = generatorKeyByPhone(passengerPhone, flag);
		String codeRedis = stringRedisTemplate.opsForValue().get(key);
		
		// 校验验证码
		System.out.println("redis中的code: " + codeRedis);
		if (codeRedis != null && codeRedis.equals(verificationCode)) {
			VerificationCodeDTO dto = new VerificationCodeDTO();
			dto.setPassengerPhone(passengerPhone);
			if (flag) {
				// 判断用户是否有过注册  TODO 这里是乘客的注册登录方案
				ResponseResult responseResult = servicePassengerUserClient.loginOrRegister(dto);
				if (responseResult.getCode() == CommonStatusEnum.USER_EXISTS.getCode()) {
					return ResponseResult.fail(CommonStatusEnum.USER_EXISTS.getCode(), CommonStatusEnum.USER_EXISTS.getMessage());
				}
			} else {
				// TODO 此处需要一个司机的注册登录方案
				ResponseResult<DriverPhoneExists> result = serviceDriverUserClient.checkDriverByDriverPhone(passengerPhone);
				Integer ifExists = result.getData().getIfExists();
				if (ifExists > 0) {
					return ResponseResult.fail(CommonStatusEnum.USER_EXISTS.getCode(), CommonStatusEnum.USER_EXISTS.getMessage());
				}
				
				DriverUser driverUser = new DriverUser();
				driverUser.setDriverPhone(passengerPhone);
				LocalDateTime now = LocalDateTime.now();
				driverUser.setGmtCreate(now);
				driverUser.setGmtModified(now);
				driverUser.setAddress("110000");
//				driverUser.setDriverBirthday(LocalDate.from(LocalDateTime.parse("2023-09-29")));
				driverUser.setCertificateNo("X60226");
				driverUser.setDriverGender(1);
				driverUser.setCommercialType(1);
				driverUser.setContractCompany("合约公司");
				driverUser.setDriverNation("08");
//				driverUser.setDriverLicenseOn(LocalDate.from(LocalDateTime.parse("2023-09-29")));
//				driverUser.setDriverLicenseOff(LocalDate.from(LocalDateTime.parse("2023-09-29")));
				
				serviceDriverUserClient.addDriver(driverUser);
				
				// 查询出他的ID
				DriverUser data = serviceDriverUserClient.findDriverByPhone(passengerPhone).getData();
				
				DriverUserWorkStatus driverUserWorkStatus = new DriverUserWorkStatus();
				driverUserWorkStatus.setGmtCreate(LocalDateTime.now());
				driverUserWorkStatus.setWorkStatus(DriverCarStatusConstant.DRIVER_CAR_STATUS_STOP);
				driverUserWorkStatus.setDriverId(data.getId());
				serviceDriverUserClient.addWorkStatus(driverUserWorkStatus);
				
			}
			
			// 删除redis中的验证码
			stringRedisTemplate.delete(generatorKeyByPhone(passengerPhone, flag));
			// 单token的颁发令牌
//			String token = JWTUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_DIENTITY);
			
			String dientity = flag ? IdentityConstant.PASSENGER_DIENTITY : IdentityConstant.DRIVER_DIENTITY;
			// 双token的颁发
			String accessToken = JWTUtils.generatorToken(passengerPhone, dientity, TokenConstant.ACCESS_TOKEN);
			String refreshToken = JWTUtils.generatorToken(passengerPhone, dientity, TokenConstant.REFRESH_TOKEN);
			
			// 将令牌放入Redis
			// accessToken
			String accessTokenKey = PrefixGeneratorUtils.generatorTokenKey(passengerPhone, dientity, TokenConstant.ACCESS_TOKEN);
			stringRedisTemplate.opsForValue()
					.set(accessTokenKey, accessToken, 14, TimeUnit.DAYS);
			// refreshToken
			String refreshTokenKey = PrefixGeneratorUtils.generatorTokenKey(passengerPhone, dientity, TokenConstant.REFRESH_TOKEN);
			stringRedisTemplate.opsForValue()
					.set(refreshTokenKey, accessToken, 30, TimeUnit.DAYS);
			
			// 响应
			TokenResponse tokenResponse = new TokenResponse();
			tokenResponse.setAccessToken(accessToken);
			tokenResponse.setRefreshToken(refreshToken);
			return ResponseResult.success(tokenResponse);
		}
		// 验证码不正确
		return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getMessage());
	}
	
	
	/**
	 * 根据手机号生成key
	 *
	 * @param passengerPhone
	 * @return
	 */
	private static String generatorKeyByPhone(String passengerPhone, boolean flag) {
		return VERIFICATIONCODEPREFIX + passengerPhone + "-" + (flag ? PASSENGER : DRIVER);
	}
}
