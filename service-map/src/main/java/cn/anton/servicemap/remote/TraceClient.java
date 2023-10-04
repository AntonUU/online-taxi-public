package cn.anton.servicemap.remote;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.internalcommon.response.TraceResponse;
import cn.anton.servicemap.constant.AMapConfigConstant;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/10/4 10:17
 */
@Service
public class TraceClient {
	
	@Value("${amap.sid}")
	private String sid;
	
	@Value("${amap.key}")
	private String key;
	
	@Resource
	private RestTemplate restTemplate;
	
	
	public ResponseResult add(String tid) {
		StringBuilder sb = new StringBuilder(AMapConfigConstant.TRACE_CREATE_URL);
		sb.append("?key=").append(key);
		sb.append("&sid=").append(sid);
		sb.append("&tid=").append(tid);
		
		ResponseEntity<String> postForEntity = restTemplate.postForEntity(sb.toString(), null, String.class);
		JSONObject jsonObject = JSONObject.fromObject(postForEntity.getBody());
		
		JSONObject data = jsonObject.getJSONObject("data");
		
		TraceResponse traceResponse = new TraceResponse();
		traceResponse.setTrid(data.getString("trid"));
		String trname = "";
		if (data.has("trname")) {
			trname = data.getString("trname");
		}
		traceResponse.setTrname(trname);
		return ResponseResult.success(traceResponse);
	}
	
}
