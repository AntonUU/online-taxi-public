package cn.anton.servicedriveruser.controller;

import cn.anton.internalcommon.dao.DriverUser;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.servicedriveruser.mapper.DriverUserMapper;
import cn.anton.servicedriveruser.service.DriverUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/9/29 07:28
 */
@RestController
public class DriverUserController {
	
	@Resource
	private DriverUserService driverUserService;
	
	/**
	 * 添加司机信息, ID必须为空
	 * @param driverUser
	 * @return
	 */
	@PostMapping("/user")
	public ResponseResult addDriver(@RequestBody DriverUser driverUser){
		System.out.println("=====addDriver执行了=====");
		return driverUserService.addDriver(driverUser);
	}
	
	/**
	 * 维护司机信息, ID不能为空
	 * @param driverUser
	 * @return
	 */
	@PutMapping("/user")
	public ResponseResult updateDriver(@RequestBody DriverUser driverUser){
		System.out.println("=====updateDriver执行了=====");
		return driverUserService.updateDriver(driverUser);
	}
	
	@RequestMapping("/test")
	public ResponseResult testController() {
//		DriverUser driverUser = driverUserMapper.selectById(1);
//		return ResponseResult.success(driverUser);
		return ResponseResult.success();
	}
	
	@GetMapping("/check-driver/{driverPhone}")
	public ResponseResult checkDriverByDriverPhone(@PathVariable String driverPhone){
		return driverUserService.checkDriverByDriverPhone(driverPhone);
	}
	
	@PostMapping("/find-driver/{driverPhone}")
	public ResponseResult findDriverByPhone(@PathVariable String driverPhone){
		return driverUserService.findDriverByPhone(driverPhone);
	}
	
}
