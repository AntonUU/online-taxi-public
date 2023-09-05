package cn.anton.apipassenger.service.impl;

import cn.anton.apipassenger.service.VerificationCodeService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/*
 * @author: Anton
 * @create_date: 2023/9/5 14:57
 */
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
	
	@Override
	public String generateCode(String passengerPhon) {
		
		// 调用电信API， 获取验证码
		System.out.println("调用电信API， 获取验证码");
		
		
		// 存入Redis
		System.out.println("存入Redis");
		
		JSONObject result = new JSONObject();
		result.put("code", 1);
		result.put("message", "success");
		
		return result.toString();
	}
}
