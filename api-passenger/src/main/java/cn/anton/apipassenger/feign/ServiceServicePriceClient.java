package cn.anton.apipassenger.feign;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.ForecastPriceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
 * 计价服务
 * @author: Anton
 * @create_date: 2023/9/22 19:41
 */
@FeignClient("service-price")
public interface ServiceServicePriceClient {
 
 @PostMapping("/forecast-price")
 ResponseResult forecastPrice(@RequestBody ForecastPriceDTO dto);
 
}
