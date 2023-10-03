package cn.anton.servicemap.remote;

import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.servicemap.constant.AMapConfigConstant;
import cn.anton.servicemap.constant.DicDistrictConstant;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/9/25 15:13
 */
@Service
public class DicDistrictClient {
	
	@Resource
	private RestTemplate restTemplate;
	
	public String dicDistrict() {
		
		// https://restapi.amap.com/v3/config/district?
		// keywords=中国
		// &subdistrict=3
		// &key=3accf5f8347ba6031840a9158d194728
		// 组装URL
		String URL = generateURL(DicDistrictConstant.KEYWORDS, DicDistrictConstant.SUB_DISTRICT);
		
		// 请求URL
		ResponseEntity<String> result = restTemplate.getForEntity(URL, String.class);
		
		
		return result.getBody();
	}
	
	private String generateURL(String keywords, String subDistrict) {
		StringBuilder sb = new StringBuilder(DicDistrictConstant.URL);
		sb.append("?").append(DicDistrictConstant.KEY_KEYWORDS).append("=").append(keywords)
				.append("&").append(DicDistrictConstant.KEY_SUB_DISTRICT).append("=").append(subDistrict)
				.append("&").append("key=").append(AMapConfigConstant.KEY_VALUE);
		
		
		return sb.toString();
	}
	
}
