package cn.anton.apiboss.remote;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.response.TraceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * @author: Anton
 * @create_date: 2023/10/4 09:23
 */
@FeignClient("service-map")
public interface ServiceMapClient {
	
	@PostMapping("/terminal/add")
	ResponseResult<String> add(@RequestParam("name") String name, @RequestParam("desc") String desc);
	
	@PostMapping("/trace/add")
	ResponseResult<TraceResponse> addTrace(@RequestParam("tid") String tid);
	
	}
