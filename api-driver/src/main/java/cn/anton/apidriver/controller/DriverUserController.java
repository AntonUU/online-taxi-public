package cn.anton.apidriver.controller;

import cn.anton.apidriver.service.DriverUserService;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.VerificationCodeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/2 12:05
 */
@RestController
public class DriverUserController {

	@Resource
	private DriverUserService driverUserService;
	
	@GetMapping("/verification-code")
	public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {
		return driverUserService.verificationCode(verificationCodeDTO.getDriverPhone());
	}
	
	@PostMapping("/verification-code-check")
	public ResponseResult verificationCodeCheck(@RequestBody VerificationCodeDTO verificationCodeDTO) {
		return driverUserService.verificationCodeCheck(verificationCodeDTO);
	}
	
	

}
