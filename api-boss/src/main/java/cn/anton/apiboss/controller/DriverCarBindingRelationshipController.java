package cn.anton.apiboss.controller;

import cn.anton.apiboss.service.DriverCarBindingRelationshipService;
import cn.anton.internalcommon.dao.DriverCarBindingRelationship;
import cn.anton.internalcommon.dao.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/2 09:52
 */
@RestController
public class DriverCarBindingRelationshipController {
	
	@Resource
	private DriverCarBindingRelationshipService driverCarBindingRelationshipService;
	
	@PostMapping("/driver-car-binding-relationship/bind")
	public ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship) {
		return driverCarBindingRelationshipService.bind(driverCarBindingRelationship);
	}
	
	@PostMapping("/driver-car-binding-relationship/unbind")
	public ResponseResult unbind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship) {
		return driverCarBindingRelationshipService.unbind(driverCarBindingRelationship);
	}
	
	
}
