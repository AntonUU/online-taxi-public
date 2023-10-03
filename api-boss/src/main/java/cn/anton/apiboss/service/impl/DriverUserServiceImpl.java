package cn.anton.apiboss.service.impl;

import cn.anton.apiboss.remote.ServiceDriverUserClient;
import cn.anton.apiboss.service.DriverUserService;
import cn.anton.internalcommon.dao.DriverUser;
import cn.anton.internalcommon.dao.ResponseResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/9/29 16:36
 */
@Service
public class DriverUserServiceImpl implements DriverUserService {
	
	@Resource
	private ServiceDriverUserClient serviceDriverUserClient;
	
	@Override
	public ResponseResult addDriverUser(DriverUser driverUser) {
		return serviceDriverUserClient.addDriver(driverUser);
	}
	
	@Override
	public ResponseResult updateDriverUser(DriverUser driverUser) {
		return serviceDriverUserClient.updateDriver(driverUser);
	}
}
