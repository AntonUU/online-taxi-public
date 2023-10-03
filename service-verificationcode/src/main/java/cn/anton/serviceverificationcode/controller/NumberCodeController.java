package cn.anton.serviceverificationcode.controller;

import cn.anton.internalcommon.dao.ResponseResult;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author: Anton
 * @create_date: 2023/9/5 18:08
 */
@RestController
public class NumberCodeController {

	@GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") Integer size){
		// 自定义生成随机数
		int result = (int) ((Math.random() * 9 + 1) * Math.pow(10, size - 1));
		System.out.println("本次生成验证码: " + result);
		return ResponseResult.success(result);
    }
	
}
