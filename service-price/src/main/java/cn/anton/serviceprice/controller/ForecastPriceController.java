package cn.anton.serviceprice.controller;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.ForecastPriceDTO;
import cn.anton.serviceprice.service.ForecastPriceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/9/22 19:32
 */
@RestController
public class ForecastPriceController {

	@Resource
	private ForecastPriceService forecastPriceService;
	
	@PostMapping("/forecast-price")
	public ResponseResult forecastPrice(@RequestBody ForecastPriceDTO dto){
		
		ResponseResult result = forecastPriceService.forecastPrice(dto);
		
		return result;
	}

}
