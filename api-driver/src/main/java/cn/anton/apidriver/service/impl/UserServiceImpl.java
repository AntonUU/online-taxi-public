package cn.anton.apidriver.service.impl;

import cn.anton.apidriver.remote.ServiceDriverUserClient;
import cn.anton.apidriver.service.UserService;
import cn.anton.internalcommon.dao.DriverUser;
import cn.anton.internalcommon.dao.ResponseResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/9/29 17:29
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private ServiceDriverUserClient serviceDriverUserClient;
	
	@Override
	public ResponseResult updateDriver(DriverUser driverUser) {
		return serviceDriverUserClient.updateDriver(driverUser);
	}
}
