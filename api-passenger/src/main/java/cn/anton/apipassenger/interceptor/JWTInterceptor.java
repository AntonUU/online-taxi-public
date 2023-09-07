package cn.anton.apipassenger.interceptor;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.util.JWTUtils;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/*
 * @author: Anton
 * @create_date: 2023/9/7 21:16
 */
public class JWTInterceptor implements HandlerInterceptor {
	
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
			JWTUtils.parseToken(token);
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
