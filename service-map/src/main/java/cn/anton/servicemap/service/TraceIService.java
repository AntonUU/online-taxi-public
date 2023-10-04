package cn.anton.servicemap.service;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.servicemap.remote.TraceClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/4 10:16
 */
@Service
public class TraceIService {
	
	@Resource
	private TraceClient traceClient;
	
	public ResponseResult add(String tid) {
		return 	traceClient.add(tid);
	}
	
}
