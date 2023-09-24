package cn.anton.servicemap.service.impl;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.ForecastPriceDTO;
import cn.anton.internalcommon.response.DirectionResponse;
import cn.anton.servicemap.remote.MapDirectionClient;
import cn.anton.servicemap.service.DirectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/*
 * @author: Anton
 * @create_date: 2023/9/22 20:30
 */
@Service
public class DirectionServiceImpl implements DirectionService {
	
	@Resource
	private MapDirectionClient mapDirectionClient;
	
	@Override
	public ResponseResult driving(ForecastPriceDTO dto) {
		// 调用第三方地图接口  目前使用高德Web服务API
		DirectionResponse direction = mapDirectionClient.direction(dto.getDepLongitude(), dto.getDepLatitude(), dto.getDestLongitude(), dto.getDestLatitude());
		
		return ResponseResult.success(direction);
	}
}
