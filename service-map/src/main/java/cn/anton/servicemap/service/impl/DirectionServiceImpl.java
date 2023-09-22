package cn.anton.servicemap.service.impl;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.ForecastPriceDTO;
import cn.anton.internalcommon.response.DirectionResponse;
import cn.anton.servicemap.service.DirectionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/*
 * @author: Anton
 * @create_date: 2023/9/22 20:30
 */
@Service
public class DirectionServiceImpl implements DirectionService {
	
	@Override
	public ResponseResult driving(ForecastPriceDTO dto) {
		DirectionResponse directionResponse = new DirectionResponse();
		directionResponse.setDistance(12000);
		directionResponse.setStrategy("最快行驶");
		directionResponse.setDuration(1020);
		directionResponse.setTolls(new BigDecimal("0"));
		directionResponse.setTollDistance(0);
		directionResponse.setTrafficLights(21);
		
		return ResponseResult.success(directionResponse);
	}
}
