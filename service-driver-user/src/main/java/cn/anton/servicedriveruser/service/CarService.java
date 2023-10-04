package cn.anton.servicedriveruser.service;

import cn.anton.internalcommon.dao.Car;
import cn.anton.internalcommon.dao.ResponseResult;

/*
 * @author: Anton
 * @create_date: 2023/10/1 07:52
 */
public interface CarService {
	
	/**
	 * 添加车辆信息
	 * @param car
	 * @return
	 */
	ResponseResult addCar(Car car);
	
	/**
	 * 查询单个司机用户信息
	 * @param carId
	 * @return
	 */
	Car findCatById(Long carId);
	
	ResponseResult getCarById(Long id);
}
