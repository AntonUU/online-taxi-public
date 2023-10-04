package cn.anton.apidriver.remote;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.PointUpLoadDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
 * @author: Anton
 * @create_date: 2023/10/4 19:29
 */
@FeignClient("service-map")
public interface ServiceMapClient {
	
	@PostMapping("/point/upload")
	ResponseResult upload(@RequestBody PointUpLoadDTO pointUpLoadDTO);
	
}
