package cn.anton.apipassenger.service.impl;

import cn.anton.apipassenger.feign.ServiceServicePriceClient;
import cn.anton.apipassenger.service.ForecastPriceService;
import cn.anton.internalcommon.constant.CommonStatusEnum;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.ForecastPriceDTO;
import cn.anton.internalcommon.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
 * 预估价格的计算实现
 * @author: Anton
 * @create_date: 2023/9/22 17:58
 */
@Service
@Slf4j
public class ForecastPriceServiceImpl implements ForecastPriceService {
	
	@Resource
	private ServiceServicePriceClient serviceServicePriceClient;
	
	@Override
	public ResponseResult forecastPrice(ForecastPriceDTO dto) {
		log.info("调用计价服务-计算价格: service-price");
		ResponseResult<ForecastPriceResponse> result = serviceServicePriceClient.forecastPrice(dto);
		ForecastPriceResponse data = result.getData();
		if (data == null) {
			return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_ERROR.getCode(), CommonStatusEnum.PRICE_RULE_ERROR.getMessage());
		}
		System.out.println("service-price(Result): " + data);
		Double price = data.getPrice();
		log.info(" service-price计算出的价格: {}", price);
		
		return ResponseResult.success(data);
		
	}
}
