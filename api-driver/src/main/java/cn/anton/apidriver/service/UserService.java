package cn.anton.apidriver.service;

import cn.anton.internalcommon.dao.DriverUser;
import cn.anton.internalcommon.dao.ResponseResult;

/*
 * @author: Anton
 * @create_date: 2023/9/29 17:29
 */
public interface UserService {
	ResponseResult updateDriver(DriverUser driverUser);
}
