package cn.anton.apipassenger.feign;

import cn.anton.internalcommon.dao.PassengerUser;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
 * @author: Anton
 * @create_date: 2023/9/7 15:18
 */
@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {
	
	@PostMapping("/user")
	ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO dto);
	
	@GetMapping("/user/{passengerPhone}")
	ResponseResult<PassengerUser> getUser(@PathVariable("passengerPhone") String passengerPhone);
	
}



