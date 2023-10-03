package cn.anton.apiboss.controller;

import cn.anton.apiboss.service.CarService;
import cn.anton.internalcommon.dao.Car;
import cn.anton.internalcommon.dao.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/2 09:25
 */
@RestController
public class CarController {
	
	@Resource
	private CarService carService;
	
	@PostMapping("/car")
	public ResponseResult addCar(@RequestBody Car car) {
		return carService.addCar(car);
	}
	
}
