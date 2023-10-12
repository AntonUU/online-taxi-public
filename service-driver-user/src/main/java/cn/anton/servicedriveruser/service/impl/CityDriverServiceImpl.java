package cn.anton.servicedriveruser.service.impl;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.servicedriveruser.mapper.DriverUserMapper;
import cn.anton.servicedriveruser.service.CityDriverService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/8 08:36
 */
@Service
public class CityDriverServiceImpl implements CityDriverService {
	
	@Resource
	private DriverUserMapper driverUserMapper;
	
	@Override
	public ResponseResult<Boolean> selectCityDriverUserByCityCode(String cityCode) {
		
		Integer count = driverUserMapper.selectCityDriverUserByCityCode(cityCode);
		
		return ResponseResult.success(count > 0);
	}
}
