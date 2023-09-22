package cn.anton.serviceprice.service.impl;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.ForecastPriceDTO;
import cn.anton.internalcommon.response.ForecastPriceResponse;
import cn.anton.serviceprice.service.ForecastPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/*
 * @author: Anton
 * @create_date: 2023/9/22 19:35
 */
@Service
@Slf4j
public class ForecastPriceServiceImpl implements ForecastPriceService {
	
	@Override
	public ResponseResult forecastPrice(ForecastPriceDTO dto) {
		ForecastPriceResponse response = new ForecastPriceResponse();
		
		log.info("调用地图服务-查询距离和市场: service-map");
	
		
		log.info("读取计价规则");
		
		log.info("根据距离，时长和计价规则，计算价格");
		
		response.setPrice(88.66);
		return ResponseResult.success(response);
	}
}
