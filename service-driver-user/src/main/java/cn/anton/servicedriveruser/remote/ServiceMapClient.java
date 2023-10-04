package cn.anton.servicedriveruser.remote;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.response.TraceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * @author: Anton
 * @create_date: 2023/10/4 23:09
 */
@FeignClient("service-map")
public interface ServiceMapClient {
	
	/**
	 * 新建终端
	 * @param name 车牌号
	 * @param desc carId
	 * @return
	 */
	@PostMapping("/terminal/add")
	ResponseResult<String> add(@RequestParam("name") String name, @RequestParam("desc") String desc);
	
	@PostMapping("/trace/add")
	ResponseResult<TraceResponse> addTrace(@RequestParam String tid);
	
	}
