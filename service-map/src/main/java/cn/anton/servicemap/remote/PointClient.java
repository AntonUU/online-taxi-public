package cn.anton.servicemap.remote;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.request.PointUpLoadDTO;
import cn.anton.servicemap.constant.AMapConfigConstant;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/*
 * @author: Anton
 * @create_date: 2023/10/4 16:05
 */
@Service
public class PointClient {
	
	@Value("${amap.key}")
	private String key;
	@Value("${amap.sid}")
	private String sid;
	@Resource
	private RestTemplate restTemplate;
	
	public ResponseResult upload(PointUpLoadDTO pointUpLoadDTO) {
		
		HttpHeaders headers = new HttpHeaders();
		// 头信息: application/x-www-form-urlencoded
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>() {{
			add("key", key);
			add("sid", sid);
			add("tid", pointUpLoadDTO.getTid());
			add("points", JSONArray.fromObject(pointUpLoadDTO.getPoints()).toString());
		}};
		HttpEntity<LinkedMultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
		String json = restTemplate.postForEntity(AMapConfigConstant.POINT_UPDATE_URL, request, String.class).getBody();
		
		JSONObject jsonObject = JSONObject.fromObject(json);
		JSONObject data = jsonObject.getJSONObject("data");
		
		System.out.println("高德API调用结果: " + jsonObject.toString());
		
		
		return ResponseResult.success();
	}
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}
	
	
}
