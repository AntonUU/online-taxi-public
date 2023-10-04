package cn.anton.servicemap.service;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.PointUpLoadDTO;
import cn.anton.servicemap.remote.PointClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/4 16:05
 */
@Service
public class PointService {
	
	@Resource
	private PointClient pointClient;
	
	public ResponseResult upload(PointUpLoadDTO pointUpLoadDTO) {
		
		return pointClient.upload(pointUpLoadDTO);
	}
}
