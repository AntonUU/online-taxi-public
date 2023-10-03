package cn.anton.apidriver.remote;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.VerificationCodeDTO;
import cn.anton.internalcommon.response.TokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
 * @author: Anton
 * @create_date: 2023/10/2 12:32
 */
@FeignClient("api-passenger")
public interface ApiPassengerCodeClient {
	
	@GetMapping("/verification-code")
	ResponseResult verificationCode(@RequestBody VerificationCodeDTO dto);
	
	@PostMapping("/verification-code-check")
	ResponseResult<TokenResponse> verificationCodeCheck(@RequestBody VerificationCodeDTO dto);
}