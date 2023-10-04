package cn.anton.apidriver.remote;

import cn.anton.internalcommon.dao.Car;
import cn.anton.internalcommon.dao.DriverPhoneExists;
import cn.anton.internalcommon.dao.DriverUser;
import cn.anton.internalcommon.dao.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/*
 * @author: Anton
 * @create_date: 2023/9/29 17:11
 */
@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {
	
	/**
	 * 维护司机信息
	 */
	@PutMapping("/user")
	ResponseResult updateDriver(@RequestBody DriverUser driverUser);
	
	/**
	 * 检查司机对应手机号是否存在
	 */
	@GetMapping("/check-driver/{driverPhone}")
	ResponseResult<DriverPhoneExists> checkDriverByDriverPhone(@PathVariable String driverPhone);
	
	@GetMapping("/car")
	ResponseResult<Car> getCarById(@RequestParam("carId") Long carId);
	
	
}
