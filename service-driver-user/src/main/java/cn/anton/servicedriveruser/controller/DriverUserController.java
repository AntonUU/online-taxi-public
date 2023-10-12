package cn.anton.servicedriveruser.controller;

import cn.anton.internalcommon.dao.DriverUser;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.response.OrderDriverResponse;
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
	
	@Resource
	private DriverUserMapper driverUserMapper;
	
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
	
	@GetMapping("/check-driver/{driverPhone}")
	public ResponseResult checkDriverByDriverPhone(@PathVariable String driverPhone){
		return driverUserService.checkDriverByDriverPhone(driverPhone);
	}
	
	@PostMapping("/find-driver/{driverPhone}")
	public ResponseResult findDriverByPhone(@PathVariable String driverPhone){
		return driverUserService.findDriverByPhone(driverPhone);
	}
	
	/**
	 * 根据车辆Id查询订单需要的司机信息
	 * @param carId
	 * @return
	 */
	@GetMapping("/get-available-driver/{carId}")
	public ResponseResult<OrderDriverResponse> getAvailableDriver(@PathVariable("carId") String carId){
		
		return driverUserService.getAvailableDriver(carId);
	}
	
}
