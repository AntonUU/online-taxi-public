package cn.anton.apiboss.controller;

import cn.anton.apiboss.service.DriverUserService;
import cn.anton.internalcommon.dao.DriverUser;
import cn.anton.internalcommon.dao.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/9/29 08:36
 */
@RestController
public class DriverUserController {
	
	@Resource
	private DriverUserService driverUserService;
	
	/**
	 * 添加司机
	 * @param driverUser
	 * @return
	 */
	@PostMapping("/driver-user")
	public ResponseResult addDriverUser(@RequestBody DriverUser driverUser) {
		ResponseResult result = driverUserService.addDriverUser(driverUser);
		return result;
	}
	
	/**
	 * 修改司机信息
	 * @param driverUser
	 * @return
	 */
	@PutMapping("/driver-user")
	public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser) {
		ResponseResult result = driverUserService.updateDriverUser(driverUser);
		return result;
	}
	
}
