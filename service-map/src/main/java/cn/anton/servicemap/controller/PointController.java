package cn.anton.servicemap.controller;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.PointUpLoadDTO;
import cn.anton.servicemap.service.PointService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/4 16:05
 */
@RestController
@RequestMapping("/point")
public class PointController {
	
	@Resource
	private PointService pointService;
	
	@PostMapping("/upload")
	public ResponseResult upload(@RequestBody PointUpLoadDTO pointUpLoadDTO) {
		return pointService.upload(pointUpLoadDTO);
	}
	
}
