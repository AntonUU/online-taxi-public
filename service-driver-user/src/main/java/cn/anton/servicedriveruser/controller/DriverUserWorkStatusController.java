package cn.anton.servicedriveruser.controller;


import cn.anton.internalcommon.dao.DriverUserWorkStatus;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.servicedriveruser.service.IDriverUserWorkStatusService;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 丶Anton
 * @since 2023-10-03
 */
@RestController
public class DriverUserWorkStatusController {

	@Resource
	private IDriverUserWorkStatusService driverUserWorkStatusServiceImpl;
	
	@PostMapping("/driver-user-work-status")
	public ResponseResult changeWorkStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus){
		return driverUserWorkStatusServiceImpl.changeWorkStatus(driverUserWorkStatus);
	}
	
	@PutMapping("/driver-user-work-status")
	public ResponseResult addWorkStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus){
		return driverUserWorkStatusServiceImpl.addWorkStatus(driverUserWorkStatus);
	}
	
	/**
	 * 通过车辆Id获取司机工作状态
	 * @param carId 车辆Id
	 * @return Boolean
	 */
	@PostMapping("/get-driver-work-status/{carId}")
	public ResponseResult getStatusByCarId(@PathVariable String carId){
		return driverUserWorkStatusServiceImpl.getStatusByCarId(carId);
	}
	
}
