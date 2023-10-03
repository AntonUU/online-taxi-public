package cn.anton.apidriver.controller;

import cn.anton.apidriver.service.UserService;
import cn.anton.internalcommon.dao.DriverUser;
import cn.anton.internalcommon.dao.ResponseResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/9/29 17:29
 */
@RestController
public class UserController {

	@Resource
	private UserService userService;
	
	@PutMapping("/user")
	public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser){
		return userService.updateDriver(driverUser);
	}

}
