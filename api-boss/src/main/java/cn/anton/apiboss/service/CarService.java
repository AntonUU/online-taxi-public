package cn.anton.apiboss.service;

import cn.anton.internalcommon.dao.Car;
import cn.anton.internalcommon.dao.ResponseResult;
import org.springframework.web.bind.annotation.RequestBody;

/*
 * @author: Anton
 * @create_date: 2023/10/2 09:23
 */
public interface CarService {
	
	ResponseResult addCar(@RequestBody Car car);
	
}
