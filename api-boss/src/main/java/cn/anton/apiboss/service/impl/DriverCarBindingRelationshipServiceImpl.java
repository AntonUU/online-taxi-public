package cn.anton.apiboss.service.impl;

import cn.anton.apiboss.remote.ServiceDriverUserClient;
import cn.anton.apiboss.service.DriverCarBindingRelationshipService;
import cn.anton.internalcommon.dao.DriverCarBindingRelationship;
import cn.anton.internalcommon.dao.ResponseResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/2 09:51
 */
@Service
public class DriverCarBindingRelationshipServiceImpl implements DriverCarBindingRelationshipService {
	
	@Resource
	private ServiceDriverUserClient serviceDriverUserClient;
	
	@Override
	public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship) {
		return serviceDriverUserClient.bind(driverCarBindingRelationship);
	}
	
	@Override
	public ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship) {
		return serviceDriverUserClient.unbind(driverCarBindingRelationship);
	}
}
