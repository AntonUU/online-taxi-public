package cn.anton.apipassenger.feign;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
 * @author: Anton
 * @create_date: 2023/10/6 13:33
 */
@FeignClient("service-order")
public interface ServiceOrderClient {
	
	@PostMapping("/order/add")
	ResponseResult add(@RequestBody OrderRequest orderRequest);
	
}
