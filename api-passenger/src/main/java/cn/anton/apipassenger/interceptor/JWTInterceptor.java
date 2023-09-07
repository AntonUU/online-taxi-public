package cn.anton.apipassenger.interceptor;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.dao.TokenResult;
import cn.anton.internalcommon.util.JWTUtils;
import cn.anton.internalcommon.util.PrefixGeneratorUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import net.sf.json.JSONObject;
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
	
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	
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
		
		String token = request.getHeader("Authorization");
		try {
			TokenResult tokenResult = JWTUtils.parseToken(token);
			String s1 = PrefixGeneratorUtils.generatorTokenKey(tokenResult.getPassengerPhone(), tokenResult.getIdentity());
			System.out.println("生成的key：" + s1);
			String s = stringRedisTemplate.opsForValue().get(s1);
			System.out.println("得到的val" + s);
			if (s == null || "".equals(s)){
				throw new SignatureVerificationException(Algorithm.HMAC256(token));
			}
		} catch (SignatureVerificationException e) {
			result = false;
			resultString = "token sign error...";
		} catch (TokenExpiredException e) {
			result = false;
			resultString = "token time out...";
		} catch (AlgorithmMismatchException e){
			result = false;
			resultString = "token AlgorithmMismatchException";
		} catch (Exception e) {
			result = false;
			resultString = "token invalid";
		}
		
		if (!result) {
			PrintWriter writer = response.getWriter();
			writer.print(JSONObject.fromObject(ResponseResult.fail(resultString)));
		}
		
		return result;
	}
}
