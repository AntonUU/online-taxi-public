package cn.anton.servicepassengeruser.controller;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.VerificationCodeDTO;
import cn.anton.servicepassengeruser.service.UserService;
import cn.anton.servicepassengeruser.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/9/7 13:00
 */
@RestController
public class UserController {
	
	@Resource
	private UserService userService;
	
	@PostMapping("/user")
	public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO dto) {
		
		String passengerPhone = dto.getPassengerPhone();
		System.out.println("乘客手机号：" + passengerPhone);
		
		ResponseResult result = userService.loginOrRegister(passengerPhone);
		
		return result;
	}
	
	@GetMapping("/user/{passengerPhone}")
	public ResponseResult getUser(@PathVariable("passengerPhone") String passengerPhone){
		ResponseResult result = userService.getUser(passengerPhone);
		return result;
	}
	
	
}
