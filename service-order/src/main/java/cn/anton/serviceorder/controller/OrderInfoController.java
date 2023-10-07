package cn.anton.serviceorder.controller;

import cn.anton.internalcommon.constant.CommonStatusEnum;
import cn.anton.internalcommon.constant.HeaderParamConstant;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.OrderRequest;
import cn.anton.serviceorder.service.OrderInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/*
 * @author: Anton
 * @create_date: 2023/10/6 13:31
 */
@RestController
@RequestMapping("/order")
public class OrderInfoController {
	
	@Resource
	private OrderInfoService orderInfoService;
	
	@PostMapping("/add")
	public ResponseResult add(@RequestBody OrderRequest orderRequest, HttpServletRequest httpServletRequest) {
//		String header = httpServletRequest.getHeader(HeaderParamConstant.DEVICE_NUMBER_NAME);
//		System.out.println("设备编号: " + header);
//		if (header == null) {
//			return ResponseResult.fail(CommonStatusEnum.FAIL.getCode(), CommonStatusEnum.FAIL.getMessage());
//		}
//		orderRequest.setDeviceCode(header);
		
		return orderInfoService.add(orderRequest);
	}
	
}
