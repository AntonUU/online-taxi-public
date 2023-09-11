package cn.anton.apipassenger.service.impl;

import cn.anton.apipassenger.feign.ServicePassengerUserClient;
import cn.anton.apipassenger.service.UserService;
import cn.anton.internalcommon.dao.PassengerUser;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.dao.TokenResult;
import cn.anton.internalcommon.util.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/9/10 14:26
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	@Resource
	private ServicePassengerUserClient servicePassengerUserClient;
	
	@Override
	public ResponseResult getUser(String accessToken) {
		
		// 解析accessToken拿到手机号
		TokenResult tokenResult = JWTUtils.parseToken(accessToken);
		// 根据手机号查询
		String phone = tokenResult.getPassengerPhone();
		log.info("用户手机号： " + phone);
		
		ResponseResult user = servicePassengerUserClient.getUser(phone);
		
		return ResponseResult.success(user);
	}
}
