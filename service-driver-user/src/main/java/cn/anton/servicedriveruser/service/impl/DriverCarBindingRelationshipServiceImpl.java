package cn.anton.servicedriveruser.service.impl;

import cn.anton.internalcommon.constant.CommonStatusEnum;
import cn.anton.internalcommon.dao.Car;
import cn.anton.internalcommon.dao.DriverCarBindingRelationship;
import cn.anton.internalcommon.dao.DriverUser;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.servicedriveruser.mapper.DriverCarBindingRelationshipMapper;
import cn.anton.servicedriveruser.service.CarService;
import cn.anton.servicedriveruser.service.DriverUserService;
import cn.anton.servicedriveruser.service.IDriverCarBindingRelationshipService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 丶Anton
 * @since 2023-10-01
 */
@Service
@Slf4j
public class DriverCarBindingRelationshipServiceImpl extends ServiceImpl<DriverCarBindingRelationshipMapper, DriverCarBindingRelationship> implements IDriverCarBindingRelationshipService {
	
	@Resource
	private CarService carService;
	
	@Resource
	private DriverUserService driverUserService;
	
	@Resource
	private DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;
	
	@Override
	public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship) {
		
		// 查询司机与车辆是否存在
		Car car = carService.findCatById(driverCarBindingRelationship.getCarId());
		DriverUser driverUser = driverUserService.findDriverUserById(driverCarBindingRelationship.getDriverId());
		// 不存在返回
		log.info("接收到的CarId: {}, 查到的车辆: {}", driverCarBindingRelationship.getCarId(), car);
		log.info("接收到的DriverUserId: {}, 查到的司机: {}", driverCarBindingRelationship.getDriverId(), driverUser);
		if (car == null || driverUser == null) return ResponseResult.fail(CommonStatusEnum.CAR_DRIVER_USER_NOT_FIND.getCode(), CommonStatusEnum.CAR_DRIVER_USER_NOT_FIND.getMessage());
		
		// 查重
		List<DriverCarBindingRelationship> arr =
				driverCarBindingRelationshipMapper.selectByMap(getMapByDriverCarBindingRelationshipEntity(driverCarBindingRelationship));
		if (arr.size() > 0)
			return ResponseResult.fail(CommonStatusEnum.CAR_DRIVER_USER_EXISTS.getCode(), CommonStatusEnum.CAR_DRIVER_USER_EXISTS.getMessage());
		
		// 存在进行绑定
		LocalDateTime now = LocalDateTime.now();
		driverCarBindingRelationship.setBindingTime(now);
		driverCarBindingRelationshipMapper.insert(driverCarBindingRelationship);
		
		return ResponseResult.success();
	}
	
	@Override
	public ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship) {
		
		// 直接解绑
//		driverCarBindingRelationshipMapper.deleteByMap(getMapByDriverCarBindingRelationshipEntity(driverCarBindingRelationship));
//		driverCarBindingRelationship.setUnBindingTime(LocalDateTime.now());
//		driverCarBindingRelationshipMapper.unbindDriverCar(driverCarBindingRelationship);
		this.update(new UpdateWrapper<DriverCarBindingRelationship>()
				            .eq("car_id", driverCarBindingRelationship.getCarId())
				            .eq("driver_id", driverCarBindingRelationship.getDriverId())
				            .eq("state", 0)
				            .set("state", 1)
				            .set("un_binding_time", LocalDateTime.now()));
		
		return ResponseResult.success();
	}
	
	private Map<String, Object> getMapByDriverCarBindingRelationshipEntity(DriverCarBindingRelationship driverCarBindingRelationship){
		Long driverId = driverCarBindingRelationship.getDriverId();
		Long carId = driverCarBindingRelationship.getCarId();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("driver_id", driverId);
		map.put("car_id", carId);
		return map;
	}
}
