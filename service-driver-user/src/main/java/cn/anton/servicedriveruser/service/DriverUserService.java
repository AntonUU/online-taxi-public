package cn.anton.servicedriveruser.service;

import cn.anton.internalcommon.dao.DriverUser;
import cn.anton.internalcommon.dao.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;

/*
 * @author: Anton
 * @create_date: 2023/9/29 07:26
 */
public interface DriverUserService {
	
	/**
	 * 添加司机
	 * @param driverUser
	 * @return
	 */
	ResponseResult addDriver(DriverUser driverUser);
	
	/**
	 * 维护司机信息
	 * @param driverUser
	 * @return
	 */
	ResponseResult updateDriver(DriverUser driverUser);
	
	/**
	 * 通过ID查询单个用户信息
	 * @param driverId
	 * @return
	 */
	DriverUser findDriverUserById(Long driverId);
	
	ResponseResult checkDriverByDriverPhone(String driverPhone);
	
	/**
	 * 通过手机号查询司机
	 * @param driverPhone
	 * @return
	 */
	ResponseResult findDriverByPhone(String driverPhone);
}
