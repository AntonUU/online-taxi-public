package cn.anton.servicemap.controller;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.servicemap.service.ServiceMapService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/4 03:50
 */
@RestController
@RequestMapping("/service")
public class ServiceController {
	
	@Resource
	private ServiceMapService serviceMapService;
	
	@PostMapping("/add")
	public ResponseResult add(String name) {
		return serviceMapService.add(name);
	}
	
}
