package cn.anton.apiboss.service.impl;

import cn.anton.apiboss.remote.ServiceDriverUserClient;
import cn.anton.apiboss.service.CarService;
import cn.anton.internalcommon.dao.Car;
import cn.anton.internalcommon.dao.ResponseResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/2 09:23
 */
@Service
public class CarServiceImpl implements CarService {
	
	@Resource
	private ServiceDriverUserClient serviceDriverUserClient;
	
	@Override
	public ResponseResult addCar(Car car) {
		return serviceDriverUserClient.addCar(car);
	}
}
