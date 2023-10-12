package cn.anton.servicedriveruser.service;

import cn.anton.internalcommon.dao.ResponseResult;

/*
 * @author: Anton
 * @create_date: 2023/10/8 08:35
 */
public interface CityDriverService {
	
	ResponseResult<Boolean> selectCityDriverUserByCityCode(String cityCode);

}
