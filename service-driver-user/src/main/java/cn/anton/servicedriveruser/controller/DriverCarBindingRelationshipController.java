package cn.anton.servicedriveruser.controller;


import cn.anton.internalcommon.dao.DriverCarBindingRelationship;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.servicedriveruser.service.IDriverCarBindingRelationshipService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 丶Anton
 * @since 2023-10-01
 */
@RestController
@RequestMapping("/driver-car-binding-relationship")
public class DriverCarBindingRelationshipController {

	@Resource
	private IDriverCarBindingRelationshipService iDriverCarBindingRelationshipService;
	
	@PostMapping ("/bind")
	public ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){
		return iDriverCarBindingRelationshipService.bind(driverCarBindingRelationship);
	}
	
	@PostMapping ("/unbind")
	public ResponseResult unbind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){
		return iDriverCarBindingRelationshipService.unbind(driverCarBindingRelationship);
	}
	
}
