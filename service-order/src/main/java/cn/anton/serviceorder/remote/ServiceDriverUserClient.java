package cn.anton.serviceorder.remote;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.response.OrderDriverResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * @author: Anton
 * @create_date: 2023/10/8 08:43
 */
@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {
	
	@GetMapping("/city-driver/is-available-driver")
	ResponseResult<Boolean> isAvailableDriver(@RequestParam("cityCode") String cityCode);
	
	/**
	 * 通过carId获取司机司机工作状态
	 *
	 * @param carId 车辆Id
	 * @return Boolean.class
	 */
	@PostMapping("/get-driver-work-status/{carId}")
	ResponseResult<Boolean> getStatusByCarId(@PathVariable String carId);
	
	/**
	 * 根据车辆id查询处于工作状态的司机信息
	 * @param carId 车辆Id
	 * @return
	 */
	@GetMapping("/get-available-driver/{carId}")
	ResponseResult<OrderDriverResponse> getAvailableDriver(@PathVariable("carId") String carId);
}
