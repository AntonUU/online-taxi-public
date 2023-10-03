package cn.anton.servicedriveruser.service.impl;

import cn.anton.internalcommon.dao.DriverPhoneExists;
import cn.anton.internalcommon.dao.DriverUser;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.servicedriveruser.mapper.DriverUserMapper;
import cn.anton.servicedriveruser.service.DriverUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @author: Anton
 * @create_date: 2023/9/29 07:27
 */
@Service
public class DriverUserServiceImpl implements DriverUserService {
	
	@Resource
	private DriverUserMapper driverUserMapper;
	
	@Override
	public ResponseResult addDriver(DriverUser driverUser) {
		driverUser.setGmtCreate(LocalDateTime.now());
		driverUser.setGmtModified(LocalDateTime.now());
		System.out.println(" ============ ");
		System.out.println(" =====执行了addDriver=======");
		System.out.println(" ============ ");
		driverUserMapper.insert(driverUser);
		return ResponseResult.success();
	}
	
	@Override
	public ResponseResult updateDriver(DriverUser driverUser) {
		
		driverUser.setGmtModified(LocalDateTime.now());
		driverUserMapper.updateById(driverUser);
		
		return ResponseResult.success();
	}
	
	@Override
	public DriverUser findDriverUserById(Long driverId) {
		return driverUserMapper.selectById(driverId);
	}
	
	@Override
	public ResponseResult checkDriverByDriverPhone(String driverPhone) {
		
		Map<String, Object> driverUserMap = new HashMap<>();
        driverUserMap.put("driver_phone", driverPhone);
		List<DriverUser> driverUsers = driverUserMapper.selectByMap(driverUserMap);
		
		return ResponseResult.success(new DriverPhoneExists(driverPhone, driverUsers.size()));
	}
	
	@Override
	public ResponseResult findDriverByPhone(String driverPhone) {
		Map<String, Object> map = new HashMap<>();
		map.put("driver_phone", driverPhone);
		List<DriverUser> driverUsers = driverUserMapper.selectByMap(map);
		
		return ResponseResult.success(driverUsers.get(0));
	}
}
