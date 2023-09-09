package cn.anton.apipassenger.service.impl;

import cn.anton.apipassenger.service.TokenService;
import cn.anton.internalcommon.constant.CommonStatusEnum;
import cn.anton.internalcommon.constant.TokenConstant;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.dao.TokenResult;
import cn.anton.internalcommon.response.TokenResponse;
import cn.anton.internalcommon.util.JWTUtils;
import cn.anton.internalcommon.util.PrefixGeneratorUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/*
 * @author: Anton
 * @create_date: 2023/9/9 14:29
 */
@Service
public class TokenServiceImpl implements TokenService {
	
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	
	
	@Override
	public ResponseResult refreshToken(String refreshTokenStr) {
		// 解析refreshToken
		TokenResult tokenResult = JWTUtils.checkToken(refreshTokenStr);
		if (tokenResult == null) {
			ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(), CommonStatusEnum.TOKEN_ERROR.getMessage());
		}
		
		String identity = tokenResult.getIdentity();
		String passengerPhone = tokenResult.getPassengerPhone();
		
		// 读取redis中refreshToken  并校验
		String redisRefreshTokenKey = PrefixGeneratorUtils.generatorTokenKey(passengerPhone, identity, TokenConstant.REFRESH_TOKEN);
		String redisToken = stringRedisTemplate.opsForValue().get(redisRefreshTokenKey);
		
		if ((StringUtils.isBlank(redisToken)) || !(refreshTokenStr.trim().equals(redisToken))) {
			ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(), CommonStatusEnum.TOKEN_ERROR.getMessage());
		}
		
		// 成功生成双token
		String accessToken = JWTUtils.generatorToken(passengerPhone, identity, TokenConstant.ACCESS_TOKEN);
		String refreshToken = JWTUtils.generatorToken(passengerPhone, identity, TokenConstant.REFRESH_TOKEN);
		
		// 将双token放入redis
		String redisAccessTokenKey = PrefixGeneratorUtils.generatorTokenKey(passengerPhone, identity, TokenConstant.ACCESS_TOKEN);
		
		stringRedisTemplate.opsForValue()
				.set(redisAccessTokenKey,  accessToken, 14, TimeUnit.DAYS);
		stringRedisTemplate.opsForValue()
				.set(redisRefreshTokenKey,  refreshToken, 30, TimeUnit.DAYS);
		
		
		
		return ResponseResult.success(new TokenResponse(accessToken, refreshToken));
	}
}
