package cn.anton.servicemap.remote;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.AroundsearchRequest;
import cn.anton.internalcommon.response.TerminalResponse;
import cn.anton.servicemap.constant.AMapConfigConstant;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/*
 * @author: Anton
 * @create_date: 2023/10/4 08:43
 */
@Service
@Slf4j
public class TerminalClient {
	
	@Value("${amap.key}")
	private String key;
	@Value("${amap.sid}")
	private String sid;
	@Resource
	private RestTemplate restTemplate;
	
	public ResponseResult add(String name, String desc) {
		StringBuilder sb = new StringBuilder(AMapConfigConstant.TERMINAL_ADD_URL);
		sb.append("?").append("key=").append(key);
		sb.append("&sid=").append(sid);
		sb.append("&name=").append(name);
		sb.append("&desc=").append(desc);
		
		System.out.println("高德创建终端URL: " + sb.toString());
		
		ResponseEntity<String> postForEntity = restTemplate.postForEntity(sb.toString(), null, String.class);
		log.info("获得的返回结果: {}", postForEntity.getBody());
		JSONObject data = JSONObject.fromObject(postForEntity.getBody()).getJSONObject("data");
		String tid = data.getString("tid");
		
		return ResponseResult.success(tid);
	}
	
	public ResponseResult aroundsearch(AroundsearchRequest aroundsearchRequest) {
		StringBuilder sb = new StringBuilder(AMapConfigConstant.TERMINAL_AROUNDSEARCH_URL);
		sb.append("?").append("key=").append(key);
		sb.append("&sid=").append(sid);
		sb.append("&center=").append(aroundsearchRequest.getCenter());
		sb.append("&radius=").append(aroundsearchRequest.getRadius());
		
		ResponseEntity<String> postForEntity = restTemplate.postForEntity(sb.toString(), null, String.class);
		String body = postForEntity.getBody();
		System.out.println("周边搜索API返回值: " + body);
		
		List<TerminalResponse> responseList = new ArrayList<>();
		
		JSONObject data = JSONObject.fromObject(body).getJSONObject("data");
		JSONArray results = data.getJSONArray("results");
		for (int i = 0; i < results.size(); i++) {
			JSONObject item = results.getJSONObject(i);
			String carId = item.getString("desc");
			String tid = item.getString("tid");
			TerminalResponse terminalResponse = new TerminalResponse();
			terminalResponse.setTid(tid);
			terminalResponse.setCarId(carId);
			responseList.add(terminalResponse);
		}
		
		return ResponseResult.success(responseList);
	}
}
