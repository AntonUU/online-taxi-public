package cn.anton.servicemap.service.impl;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.servicemap.remote.ServiceClient;
import cn.anton.servicemap.service.ServiceMapService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/4 03:50
 */
@Service
public class ServiceMapServiceImpl implements ServiceMapService {

	@Resource
	private ServiceClient serviceClient;
	
	@Override
	public ResponseResult add(String name) {
		return serviceClient.add(name);
	}
}
