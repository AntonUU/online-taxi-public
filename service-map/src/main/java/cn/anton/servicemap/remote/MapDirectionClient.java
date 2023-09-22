package cn.anton.servicemap.remote;

import cn.anton.internalcommon.response.DirectionResponse;
import cn.anton.servicemap.constant.AMapConfigConstant;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;

/*
 * 用于调用第三方地图服务
 * @author: Anton
 * @create_date: 2023/9/22 21:02
 */
@Service
@Slf4j
public class MapDirectionClient {
	
	@Resource
	private RestTemplate restTemplate;
	
	
	public DirectionResponse direction(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
		// 组装接口
		// https://restapi.amap.com/v3/direction/driving?
		// origin=108.44,22.08&destination=108.36,22.85&extensions=all&output=json&key=3accf5f8347ba6031840a9158d194728
		String urlStr = generateURL(depLongitude, depLatitude, destLongitude, destLatitude);
		
		
		// 调用第三方API
		ResponseEntity<String> entity = restTemplate.getForEntity(urlStr, String.class);
		String responseJson = entity.getBody();
		log.info("第三方API返回结果: " + responseJson);
		
		//  解析接口
		DirectionResponse directionResponse = parseDirectionEntity(responseJson);
		
		return directionResponse;
	}
	
	private String generateURL(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
		StringBuilder sb = new StringBuilder(AMapConfigConstant.URL);
		sb.append("?").append(AMapConfigConstant.ORIGIN + "=").append(depLongitude).append(",").append(depLatitude)
				.append("&").append(AMapConfigConstant.DESTINATION + "=").append(destLongitude).append(",").append(destLatitude)
				.append("&").append(AMapConfigConstant.EXTENSIONS).append("=").append(AMapConfigConstant.EXTENSIONS_VALUE_ALL)
				.append("&").append(AMapConfigConstant.OUTPUT).append("=").append(AMapConfigConstant.OUTPUT_VALUE_JSON)
				.append("&").append(AMapConfigConstant.KEY).append("=").append(AMapConfigConstant.KEY_VALUE);
		
		
		return sb.toString();
	}
	
	private DirectionResponse parseDirectionEntity(String directionJsonStr){
		DirectionResponse directionResponse = null;
		try {
			directionResponse = new DirectionResponse();
			JSONObject result = JSONObject.fromObject(directionJsonStr);
			if (result.has(AMapConfigConstant.KEY_STATUS)) {
				int status = result.getInt(AMapConfigConstant.KEY_STATUS);
				if (status == 1) {
					if (result.has(AMapConfigConstant.KEY_ROUTE)) {
						JSONObject routeObject = result.getJSONObject(AMapConfigConstant.KEY_ROUTE);
						if (routeObject.has(AMapConfigConstant.KEY_PATHS)) {
							JSONArray jsonArray = routeObject.getJSONArray(AMapConfigConstant.KEY_PATHS);
							if (jsonArray.size() > 0) {
								JSONObject item0 = jsonArray.getJSONObject(0);
								Integer distance = Integer.parseInt((String) item0.get(AMapConfigConstant.KEY_DISTANCE));
								Integer tollDistance = Integer.parseInt((String) item0.get(AMapConfigConstant.KEY_TOLL_DISTANCE));
								Integer duration = Integer.parseInt((String) item0.get(AMapConfigConstant.KEY_DURATION));
								String strategy =(String) item0.get(AMapConfigConstant.KEY_STRATEGY);
								BigDecimal tolls = new BigDecimal((String) item0.get(AMapConfigConstant.KEY_TOLLS));
								Integer trafficLights = Integer.parseInt((String) item0.get(AMapConfigConstant.KEY_TRAFFIC_LIGHTS));
								
								directionResponse.setTollDistance(tollDistance);
								directionResponse.setTolls(tolls);
								directionResponse.setDuration(duration);
								directionResponse.setStrategy(strategy);
								directionResponse.setTrafficLights(trafficLights);
								directionResponse.setDistance(distance);
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			log.error("第三方API解析出错......");
		}
		
		return directionResponse;
	}
	
}
