package cn.anton.apidriver.controller;

import cn.anton.apidriver.service.PointService;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.ApiDriverPointDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/4 19:04
 */
@RestController
@RequestMapping("/point")
public class PointController {
	
	@Resource
	private PointService pointService;
	
	@PostMapping("/upload")
	public ResponseResult upload(@RequestBody ApiDriverPointDTO apiDriverPointDTO) {
		return pointService.upload(apiDriverPointDTO);
	}
	
}
