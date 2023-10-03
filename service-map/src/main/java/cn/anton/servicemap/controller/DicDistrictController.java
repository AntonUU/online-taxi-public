package cn.anton.servicemap.controller;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.servicemap.service.DicDistrictService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/9/25 11:40
 */
@RestController
public class DicDistrictController {
	
	@Resource
	private DicDistrictService dicDistrictService;
	
	@GetMapping("/init/district")
	public ResponseResult initDicDistrict() {
		ResponseResult result = dicDistrictService.initDicDistrict();
		return result;
	}
	
}
