package cn.anton.apiboss.service;

import cn.anton.internalcommon.dao.DriverUser;
import cn.anton.internalcommon.dao.ResponseResult;

/*
 * @author: Anton
 * @create_date: 2023/9/29 16:36
 */
public interface DriverUserService {
	ResponseResult addDriverUser(DriverUser driverUser);
	
	ResponseResult updateDriverUser(DriverUser driverUser);
}
