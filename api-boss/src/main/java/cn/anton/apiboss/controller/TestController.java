package cn.anton.apiboss.controller;

import cn.anton.internalcommon.dao.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/9/29 08:26
 */
@RestController
public class TestController {
	
	@GetMapping("/test")
	public ResponseResult testApi() {
		return ResponseResult.success("api-boss");
	}
	
}
