package cn.anton.servicedriveruser.service.impl;

import cn.anton.internalcommon.dao.Car;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.response.TraceResponse;
import cn.anton.servicedriveruser.mapper.CarMapper;
import cn.anton.servicedriveruser.remote.ServiceMapClient;
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
	
	@Resource
	private ServiceMapClient serviceMapClient;
	
	
	@Override
	public ResponseResult addCar(Car car) {
		LocalDateTime now = LocalDateTime.now();
		car.setGmtCreate(now);
		car.setGmtModified(now);
		carMapper.insert(car);
		String vehicleNo = car.getVehicleNo();
		
		// 新建终端
		ResponseResult<String> terminalResult = serviceMapClient.add(vehicleNo, car.getId() + "");
		String tid = terminalResult.getData();
		car.setTid(tid);
		
		// 新建轨迹
		ResponseResult<TraceResponse> traceResponse = serviceMapClient.addTrace(tid);
		TraceResponse data = traceResponse.getData();
		car.setTrid(data.getTrid());
		car.setTrname(data.getTrname());
		
		carMapper.updateById(car);
		
		return ResponseResult.success();
	}
	
	@Override
	public Car findCatById(Long carId) {
		return carMapper.selectById(carId);
	}
	
	@Override
	public ResponseResult getCarById(Long id) {
		Car car = carMapper.selectById(id);
		
		return ResponseResult.success(car);
	}
}
