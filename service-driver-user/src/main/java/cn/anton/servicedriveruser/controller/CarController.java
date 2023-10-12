package cn.anton.servicedriveruser.controller;

import cn.anton.internalcommon.dao.Car;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.servicedriveruser.service.CarService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/1 08:09
 */
@RestController
public class CarController {
	
	@Resource
	private CarService carService;
	
	/**
	 * 添加车辆信息
	 * @param car
	 * @return
	 */
	@PostMapping("/car")
	public ResponseResult addCar(@RequestBody Car car){
		return carService.addCar(car);
	}
	
	@GetMapping("/car")
	public ResponseResult getCarById(@RequestParam("carId") Long carId){
		return carService.getCarById(carId);
	}
	
}
