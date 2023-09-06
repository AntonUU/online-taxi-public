package cn.anton.apipassenger.service.impl;

import cn.anton.apipassenger.feign.ServiceVerificationcodeCilent;
import cn.anton.apipassenger.service.VerificationCodeService;
import cn.anton.internalcommon.dao.ResponseResult;
import net.sf.json.JSONObject;
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
	
	private final String verificationCodePrefix = "passenger-verification-code-";
	
	
	@Override
	public void generateCode(String passengerPhon) {
		
		// 调用电信API， 获取验证码
		ResponseResult responseResult = serviceVerificationcodeCilent.numberCode();
		Integer numberCode = (Integer) responseResult.getData();
		
		// 存入Redis
		String key = verificationCodePrefix + passengerPhon;
		String val = numberCode + "";
		stringRedisTemplate.opsForValue().set(key, val, 2, TimeUnit.MINUTES);
		
	}
}
