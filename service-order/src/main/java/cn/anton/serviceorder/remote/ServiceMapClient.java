package cn.anton.serviceorder.remote;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.AroundsearchRequest;
import cn.anton.internalcommon.response.TerminalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/*
 * @author: Anton
 * @create_date: 2023/10/8 09:05
 */
@FeignClient("service-map")
public interface ServiceMapClient {
	
	@PostMapping("/terminal/aroundsearch")
	ResponseResult<List<TerminalResponse>> aroundsearch(@RequestBody AroundsearchRequest aroundsearchRequest);
	
}
