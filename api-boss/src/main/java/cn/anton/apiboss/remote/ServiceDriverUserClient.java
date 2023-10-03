package cn.anton.apiboss.remote;

import cn.anton.internalcommon.dao.Car;
import cn.anton.internalcommon.dao.DriverCarBindingRelationship;
import cn.anton.internalcommon.dao.DriverUser;
import cn.anton.internalcommon.dao.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
 * @author: Anton
 * @create_date: 2023/9/29 08:35
 */
@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {
	
	/**
	 * 司机相关
	 */
	
	
	@PostMapping("/user")
	ResponseResult addDriver(@RequestBody DriverUser driverUser);
	
	@PutMapping("/user")
	ResponseResult updateDriver(@RequestBody DriverUser driverUser);
	
	/**
	 * 车辆相关
	 */
	
	@PostMapping("/car")
	ResponseResult addCar(@RequestBody Car car);
	
	/**
	 * 司机与车辆相关
	 */
	
	@PostMapping ("/driver-car-binding-relationship/bind")
	public ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);
	
	@PostMapping ("/driver-car-binding-relationship/unbind")
	public ResponseResult unbind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);
}