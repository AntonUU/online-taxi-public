package cn.anton.apipassenger.controller;

import cn.anton.apipassenger.service.OrderService;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.OrderRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/6 11:30
 */
@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Resource
	private OrderService orderService;
	
	/**
	 * 乘客发起订单
	 * @return
	 */
	@PostMapping("/add")
	public ResponseResult add(@RequestBody OrderRequest orderRequest) {
		return orderService.add(orderRequest);
	}
	
}
