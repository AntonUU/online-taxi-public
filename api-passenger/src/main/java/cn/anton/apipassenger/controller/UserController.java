package cn.anton.apipassenger.controller;

import cn.anton.apipassenger.service.UserService;
import cn.anton.internalcommon.dao.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/*
 * @author: Anton
 * @create_date: 2023/9/10 14:21
 */
@RestController
@Slf4j
public class UserController {
	
	@Resource
	private UserService userService;
	
	@GetMapping("/users")
	public ResponseResult getUser(HttpServletRequest request) {
		
		// 从http请求中获取accessToken
		String access_token = request.getHeader("Authorization");
		log.info(access_token);
		
		// 根据accessToken查询用户信息
		ResponseResult result = userService.getUser(access_token);
		
		return result;
	}
	
}
