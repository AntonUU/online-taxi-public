package cn.anton.internalcommon.util;

import cn.anton.internalcommon.constant.IdentityConstant;
import cn.anton.internalcommon.dao.TokenResult;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * @author: Anton
 * @create_date: 2023/9/7 16:56
 */
public class JWTUtils {
	
	// 盐
	private static final String SIGN = "LADfdcx!@$$";
	private static final String JWT_KEY_PHONE = "passengerPhone";
	/**
	 * 身份标识  乘客是1  司机是2
	 */
	private static final String JWT_KEY_IDENTITY = "identity";
	
	// 生成token
	public static String generatorToken(String passengerPhone, String identity) {
		Map<String, String> map = new HashMap<>();
		map.put(JWT_KEY_PHONE, passengerPhone);
		map.put(JWT_KEY_IDENTITY, identity);
		
		// jwt过期时间
//		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.DATE, 1);
//		Date date = calendar.getTime();
		
		JWTCreator.Builder builder = JWT.create();
		map.forEach((k, y) -> {
			builder.withClaim(k, y);
		});
//		builder.withClaim(JWT_KEY_PHONE, passengerPhone);
//		builder.withExpiresAt(date); // jwt有效时间设置
		
		String sign = builder.sign(Algorithm.HMAC256(SIGN));
		
		return sign;
	}
	
	public static void main(String[] args) {
		String passenger_phone = "1577719110";
		
		String s = JWTUtils.generatorToken(passenger_phone, IdentityConstant.DRIVER_DIENTITY);
		System.out.println("生成的Token： " + s);
		TokenResult tokenResult = JWTUtils.parseToken(s);
		System.out.println("解析的Token: " + tokenResult);
	}
	
	
	// 解析token
	public static TokenResult parseToken(String sign){
		DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(sign);
		String passengerPhone = verify.getClaim(JWT_KEY_PHONE).toString();
		String identity = verify.getClaim(JWT_KEY_IDENTITY).toString();
		
		TokenResult tokenResult = new TokenResult();
		tokenResult.setIdentity(identity);
		tokenResult.setPassengerPhone(passengerPhone);
		
		return tokenResult;
	}
	
	
}
