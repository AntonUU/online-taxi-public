package cn.anton.servicedriveruser.controller;

import cn.anton.internalcommon.dao.Car;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.servicedriveruser.service.CarService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/1 08:09
 */
@RestController
public class CatController {
	
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
	
}
