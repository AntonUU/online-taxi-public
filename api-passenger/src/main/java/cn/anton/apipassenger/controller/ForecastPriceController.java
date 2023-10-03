package cn.anton.apipassenger.controller;

import cn.anton.apipassenger.service.ForecastPriceService;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.ForecastPriceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/9/22 17:49
 */
@RestController
@Slf4j
public class ForecastPriceController {
	
	@Resource
	private ForecastPriceService forecastPriceService;
	
	
	@PostMapping("/forecast-price")
	public ResponseResult forecastPrice(@RequestBody ForecastPriceDTO dto) {
		ResponseResult result = forecastPriceService.forecastPrice(dto);
		
		return result;
	}
	
}
