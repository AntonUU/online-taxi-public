package cn.anton.serviceprice.remote;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.ForecastPriceDTO;
import cn.anton.internalcommon.response.DirectionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * @author: Anton
 * @create_date: 2023/9/22 22:20
 */
@FeignClient("service-map")
public interface ServiceMapClient {
	
	@RequestMapping(value = "/direction/driving", method = RequestMethod.GET)
	public ResponseResult<DirectionResponse> driving(@RequestBody ForecastPriceDTO dto);

}
