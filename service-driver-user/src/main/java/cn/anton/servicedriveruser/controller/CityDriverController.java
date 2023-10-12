package cn.anton.servicedriveruser.controller;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.servicedriveruser.service.CityDriverService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/8 08:34
 */
@RestController
@RequestMapping("/city-driver")
public class CityDriverController {
	
	@Resource
	private CityDriverService cityDriverService;
	
	/**
	 * 根据cityCode查看当前城市是否有司机可用
	 * @param cityCode
	 * @return
	 */
	@GetMapping("/is-available-driver")
	public ResponseResult<Boolean> isAvailableDriver(@RequestParam("cityCode") String cityCode) {
		return cityDriverService.selectCityDriverUserByCityCode(cityCode);
	}
	
}
