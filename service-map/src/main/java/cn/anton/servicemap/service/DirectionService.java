package cn.anton.servicemap.service;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.ForecastPriceDTO;
import org.springframework.web.bind.annotation.RequestBody;

/*
 * @author: Anton
 * @create_date: 2023/9/22 20:30
 */
public interface DirectionService {
	
	/**
	 * 根据起点和终点经纬度获取时长, 单位： 分钟
	 * @param dto
	 * @return
	 */
	ResponseResult driving(@RequestBody ForecastPriceDTO dto);
	
}
