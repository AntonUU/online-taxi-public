package cn.anton.serviceprice.service;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.ForecastPriceDTO;

/*
 * 预估价格的计算
 * @author: Anton
 * @create_date: 2023/9/22 17:58
 */
public interface ForecastPriceService {
	
	/**
	 * 根据 出发地与目的地 经纬度预估价格
	 * @return 预估价格
	 */
	ResponseResult forecastPrice(ForecastPriceDTO dto);
}
