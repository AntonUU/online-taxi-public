package cn.anton.servicedriveruser.service.impl;

import cn.anton.internalcommon.dao.Car;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.servicedriveruser.mapper.CarMapper;
import cn.anton.servicedriveruser.service.CarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/*
 * @author: Anton
 * @create_date: 2023/10/1 07:52
 */
@Service
public class CarServiceImpl implements CarService {
	
	@Resource
	private CarMapper carMapper;
	
	
	@Override
	public ResponseResult addCar(Car car) {
		LocalDateTime now = LocalDateTime.now();
		car.setGmtCreate(now);
		car.setGmtModified(now);
		carMapper.insert(car);
		return ResponseResult.success();
	}
	
	@Override
	public Car findCatById(Long carId) {
		return carMapper.selectById(carId);
	}
}
