package cn.anton.apipassenger.interceptor;

import cn.anton.internalcommon.constant.TokenConstant;
import cn.anton.internalcommon.dao.PassengerUser;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.dao.TokenResult;
import cn.anton.internalcommon.util.JWTUtils;
import cn.anton.internalcommon.util.PrefixGeneratorUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import io.netty.util.internal.StringUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/*
 * @author: Anton
 * @create_date: 2023/9/7 21:16
 */
public class JWTInterceptor implements HandlerInterceptor {
	
	/**
	 * JWTInterceptor是在SpringBean之前初始化的， 所以说JWTInterceptor初始化之前Bean还没有开始初始化
	 * 所以会导致Bean注入失败 StringRedisTemplate 为null， 需要在拦截器执行之前将JWTInterceptor初始化
	 */
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	@Qualifier("passengerUserThreadLocal")
	private ThreadLocal<PassengerUser> threadLocal;
	
	/**
	 * JWT拦截器
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean result = true;
		String resultString = "";
		
		System.out.println("进入了拦截器" + request.getRequestURI());
		
		String token = request.getHeader("Authorization");
		TokenResult tokenResult = JWTUtils.checkToken(token);
		
		if (tokenResult == null) {
			resultString = "token invalid";
			result = false;
		} else {
			// 生成ACCESS_TOKEN_KEY
			String accessTokenKey = PrefixGeneratorUtils.generatorTokenKey(tokenResult.getPassengerPhone(), tokenResult.getIdentity(), TokenConstant.ACCESS_TOKEN);
			String tokenRedis = stringRedisTemplate.opsForValue().get(accessTokenKey);
			
			if ((StringUtils.isBlank(tokenRedis)) || (!token.trim().equals(tokenRedis.trim()))){
				resultString = "token invalid";
				result = false;
			}
		}
		
		if (!result) {
			PrintWriter writer = response.getWriter();
			writer.print(JSONObject.fromObject(ResponseResult.fail(resultString)));
		}
		// TODO 使用线程类，传递当前用户信息， 待完成
		PassengerUser passengerUser = new PassengerUser();
		passengerUser.setPassengerPhone(tokenResult.getPassengerPhone());
		threadLocal.set(passengerUser);
		
		return result;
	}
	
}
