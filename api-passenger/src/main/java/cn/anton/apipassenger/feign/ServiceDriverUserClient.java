package cn.anton.apipassenger.feign;

import cn.anton.internalcommon.dao.DriverPhoneExists;
import cn.anton.internalcommon.dao.DriverUser;
import cn.anton.internalcommon.dao.DriverUserWorkStatus;
import cn.anton.internalcommon.dao.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/*
 * @author: Anton
 * @create_date: 2023/10/3 18:19
 */
@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {
	
	@GetMapping("/check-driver/{driverPhone}")
	ResponseResult<DriverPhoneExists> checkDriverByDriverPhone(@PathVariable String driverPhone);
	
	@PostMapping("/user")
	ResponseResult addDriver(@RequestBody DriverUser driverUser);
	
	@PutMapping("/driver-user-work-status")
	ResponseResult addWorkStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus);
	
	
	@PostMapping("/find-driver/{driverPhone}")
	ResponseResult<DriverUser> findDriverByPhone(@PathVariable String driverPhone);
	
	
}
