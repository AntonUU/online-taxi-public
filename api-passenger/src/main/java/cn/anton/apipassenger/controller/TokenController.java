package cn.anton.apipassenger.controller;

import cn.anton.apipassenger.service.TokenService;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.dao.TokenResult;
import cn.anton.internalcommon.response.TokenResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/9/9 14:26
 */
@RestController
public class TokenController {
	
	@Resource
	private TokenService tokenService;
	
    @PostMapping("/token-refresh")
	public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse) {
		
		String refresh = tokenResponse.getRefreshToken();
	    System.out.println("传入的refreshToken： " + refresh);
     
         return tokenService.refreshToken(refresh);
	}
	
}
