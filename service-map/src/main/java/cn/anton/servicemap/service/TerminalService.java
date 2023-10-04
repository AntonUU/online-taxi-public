package cn.anton.servicemap.service;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.AroundsearchRequest;
import cn.anton.servicemap.remote.TerminalClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/4 08:42
 */
@Service
public class TerminalService {
	
   @Resource
   private TerminalClient terminalClient;
 
	public ResponseResult add(String name, String desc) {
		return terminalClient.add(name, desc);
	}
	
	public ResponseResult aroundsearch(AroundsearchRequest aroundsearchRequest) {
		
		return terminalClient.aroundsearch(aroundsearchRequest);
	}
}
