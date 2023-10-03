package cn.anton.servicemap.service.impl;

import cn.anton.internalcommon.constant.CommonStatusEnum;
import cn.anton.internalcommon.dao.DicDistrict;
import cn.anton.internalcommon.dao.ResponseResult;
import cn.anton.servicemap.constant.AMapConfigConstant;
import cn.anton.servicemap.mapper.DicDistrictMapper;
import cn.anton.servicemap.remote.DicDistrictClient;
import cn.anton.servicemap.service.DicDistrictService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
 * @author: Anton
 * @create_date: 2023/9/25 11:26
 */
@Service
@Slf4j
public class DicDistrictServiceImpl implements DicDistrictService {
	
	@Resource
	private DicDistrictClient dicDistrictClient;
	
	@Resource
	private DicDistrictMapper dicDistrictMapper;
	
	@Override
	public ResponseResult initDicDistrict() {
		
		String dicDistrictJson = dicDistrictClient.dicDistrict();
		log.info("请求到的行政区域: {}", dicDistrictJson);
		
		// 解析JSON
		JSONObject dicDistrictJsonObject = JSONObject.fromObject(dicDistrictJson);
		Integer status = dicDistrictJsonObject.getInt(AMapConfigConstant.KEY_STATUS);
		if (status != 1)
			return ResponseResult.fail(CommonStatusEnum.MAP_DISTRICT_ERROR.getCode(), CommonStatusEnum.MAP_DISTRICT_ERROR.getMessage());
		JSONArray countryJSONArray = dicDistrictJsonObject.getJSONArray(AMapConfigConstant.DISTRICTS);
		
		for (int i = 0; i < countryJSONArray.size(); i++) {
			JSONObject countryJsonObject = countryJSONArray.getJSONObject(i);
			String countryAddressName = countryJsonObject.getString(AMapConfigConstant.NAME);
			String countryAddressCode = countryJsonObject.getString(AMapConfigConstant.ADCODE);
			String countryLevel = countryJsonObject.getString(AMapConfigConstant.LEVEL);
			String countryParentAddressCode = "0";
			
			insertDicDistrict(countryAddressName, countryAddressCode, countryLevel, countryParentAddressCode);
			
			JSONArray proviceJsonArray = countryJsonObject.getJSONArray(AMapConfigConstant.DISTRICTS);
			for (int p = 0; p < proviceJsonArray.size(); p++) {
				JSONObject proviceJsonObject = proviceJsonArray.getJSONObject(p);
				String proviceAddressName = proviceJsonObject.getString(AMapConfigConstant.NAME);
				String proviceAddressCode = proviceJsonObject.getString(AMapConfigConstant.ADCODE);
				String proviceLevel = proviceJsonObject.getString(AMapConfigConstant.LEVEL);
				
				insertDicDistrict(proviceAddressName, proviceAddressCode, proviceLevel, countryAddressCode);
				
				JSONArray cityJsonArray = proviceJsonObject.getJSONArray(AMapConfigConstant.DISTRICTS);
				for (int c = 0; c < cityJsonArray.size(); c++) {
					JSONObject cityJsonObject = cityJsonArray.getJSONObject(c);
					String cityAddressName = cityJsonObject.getString(AMapConfigConstant.NAME);
					String cityAddressCode = cityJsonObject.getString(AMapConfigConstant.ADCODE);
					String cityLevel = cityJsonObject.getString(AMapConfigConstant.LEVEL);
					
					if (AMapConfigConstant.STREET.equals(cityJsonObject.getString(AMapConfigConstant.LEVEL))) continue;
					
					insertDicDistrict(cityAddressName, cityAddressCode, cityLevel, proviceAddressCode);
					JSONArray districtJsonArray = cityJsonObject.getJSONArray(AMapConfigConstant.DISTRICTS);
					for (int d = 0; d < districtJsonArray.size(); d++) {
						JSONObject districtJsonObject = districtJsonArray.getJSONObject(d);
						String districtAddressName = districtJsonObject.getString(AMapConfigConstant.NAME);
						String districtAddressCode = districtJsonObject.getString(AMapConfigConstant.ADCODE);
						String districtLevel = districtJsonObject.getString(AMapConfigConstant.LEVEL);
						
						if (AMapConfigConstant.STREET.equals(districtJsonObject.getString(AMapConfigConstant.LEVEL))) continue;
						insertDicDistrict(districtAddressName, districtAddressCode, districtLevel, cityAddressCode);
					}
				}
			}
		}
		// 插入db
		
		return ResponseResult.success();
	}
	
	private void insertDicDistrict(String addressName, String addressCode, String level, String cityCode) {
		DicDistrict district = new DicDistrict();
		district.setLevel(levelStringToInteger(level));
		district.setAddressName(addressName);
		district.setParentAddressCode(cityCode);
		district.setAddressCode(addressCode);
		
		dicDistrictMapper.insert(district);
		
	}
	
	private Integer levelStringToInteger(String strLevel) {
		if ("country".equals(strLevel)) return 0;
		if ("province".equals(strLevel)) return 1;
		if ("city".equals(strLevel)) return 2;
		if ("district".equals(strLevel)) return 3;
		return -1;
	}
	
	
}
