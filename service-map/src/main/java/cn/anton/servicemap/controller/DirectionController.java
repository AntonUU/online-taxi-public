package cn.anton.servicemap.controller;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.ForecastPriceDTO;
import cn.anton.servicemap.service.DirectionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/9/22 20:27
 */
@RestController
@RequestMapping("/direction")
public class DirectionController {
	
	@Resource
	private DirectionService directionService;
	
	@GetMapping("/driving")
	public ResponseResult driving(@RequestBody ForecastPriceDTO dto) {
		ResponseResult result = directionService.driving(dto);
		
		return result;
	}
	
}
