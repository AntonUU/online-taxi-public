package cn.anton.apipassenger.controller;

import cn.anton.apipassenger.feign.ServiceVerificationcodeCilent;
import cn.anton.internalcommon.dao.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/9/5 14:27
 */
@RestController
public class TestController {
	
	@Resource
	private ServiceVerificationcodeCilent serviceVerificationcodeCilent;
	
	@RequestMapping("/test")
	public String testSuccess() {
		return "testSuccess";
	}
	
	@RequestMapping("/testFeign")
	public ResponseResult testFeign() {
		return ResponseResult.success(serviceVerificationcodeCilent.numberCode());
	}
	
	@GetMapping("/test-auth")
	public ResponseResult testAuth() {
		return ResponseResult.success("yes-auth");
	}
	
	@GetMapping("/no-auth")
	public ResponseResult testNoAuth() {
		return ResponseResult.success("no-auth");
	}
	
	
}
