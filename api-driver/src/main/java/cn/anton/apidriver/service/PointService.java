package cn.anton.apidriver.service;

import cn.anton.apidriver.remote.ServiceDriverUserClient;
import cn.anton.apidriver.remote.ServiceMapClient;
import cn.anton.internalcommon.dao.Car;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.ApiDriverPointDTO;
import cn.anton.internalcommon.request.PointUpLoadDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/4 19:05
 */
@Service
public class PointService {
	
	@Resource
	private ServiceDriverUserClient serviceDriverUserClient;
	
	@Resource
	private ServiceMapClient serviceMapClient;
	
	public ResponseResult upload(ApiDriverPointDTO apiDriverPointDTO) {
		// 读取carId
		Long carId = apiDriverPointDTO.getCarId();
		
		// 通过carId 获取tid、trid. 调用 service-driver-user接口
		ResponseResult<Car> driverUserClientCarResult = serviceDriverUserClient.getCarById(carId);
		Car car = driverUserClientCarResult.getData();
		String tid = car.getTid();
		String trid = car.getTrid();
		
		// 调用地图API上传
		PointUpLoadDTO pointUpLoadDTO = new PointUpLoadDTO();
		pointUpLoadDTO.setTid(tid);
		pointUpLoadDTO.setTrid(trid);
		pointUpLoadDTO.setPoints(apiDriverPointDTO.getPoints());
		ResponseResult upload = serviceMapClient.upload(pointUpLoadDTO);
		
		return ResponseResult.success();
	}
}
