package cn.anton.apiboss.service.impl;

import cn.anton.apiboss.remote.ServiceDriverUserClient;
import cn.anton.apiboss.remote.ServiceMapClient;
import cn.anton.apiboss.service.CarService;
import cn.anton.internalcommon.dao.Car;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.response.TraceResponse;
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
	@Resource
	private ServiceMapClient serviceMapClient;
	
	
	@Override
	public ResponseResult addCar(Car car) {
		
//		// 获得此车辆对应的tid
//		String vehicleNo = car.getVehicleNo();
//		System.out.println("车牌号: " + vehicleNo);
//
//		// 以车牌号命名作为终端名称
//		ResponseResult<String> terminalResult = serviceMapClient.add(vehicleNo, car.getId() + "");
//		String tid = terminalResult.getData();
//		car.setTid(tid);
//
//		// 获得车辆的轨迹id: trid
//		ResponseResult<TraceResponse> traceResponse = serviceMapClient.addTrace(tid);
//		TraceResponse data = traceResponse.getData();
//		car.setTrid(data.getTrid());
//		car.setTrname(data.getTrname());
		
//		return serviceDriverUserClient.addCar(car);
		return ResponseResult.success();
	}
}
