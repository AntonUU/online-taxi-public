package cn.anton.serviceorder.remote;

import cn.anton.internalcommon.dao.PriceRule;
import cn.anton.internalcommon.dao.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * @author: Anton
 * @create_date: 2023/10/7 15:07
 */
@FeignClient("service-price")
public interface ServicePriceClient {
	
	/**
	 * 判断计价规则是否是最新
	 */
	@GetMapping("/price-rule/is-new")
	ResponseResult<Boolean> isNew(@RequestParam("fareType") String fareType, @RequestParam("fareVersion") Integer fareVersion);
	
	@GetMapping("/price-rule/get-newest-version")
	ResponseResult<PriceRule> getNewestVersion(@RequestParam("fareType") String fareType);
	
	@GetMapping("/price-rule/if-exists")
	ResponseResult<Boolean> ifExists(@RequestBody PriceRule priceRule);
	
}
