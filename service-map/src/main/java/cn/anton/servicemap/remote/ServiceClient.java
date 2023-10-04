package cn.anton.servicemap.remote;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.servicemap.constant.AMapConfigConstant;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/4 03:53
 */
@Service
public class ServiceClient {
	
	@Value("${amap.key}")
	private String key;
	
	@Resource
	private RestTemplate restTemplate;
	
	public ResponseResult add(String name){
		StringBuilder sb = new StringBuilder(AMapConfigConstant.SERVICE_ADD_URL);
		sb.append("?");
		sb.append("key=").append(key);
		sb.append("&").append("name=").append(name);
		
		ResponseEntity<String> postForEntity = restTemplate.postForEntity(sb.toString(), null, String.class);
		
		String body = postForEntity.getBody();
		
		JSONObject data = JSONObject.fromObject(body).getJSONObject("data");
		String sid = data.getString("sid");
		
		return ResponseResult.success(sid);
	}
	
}
