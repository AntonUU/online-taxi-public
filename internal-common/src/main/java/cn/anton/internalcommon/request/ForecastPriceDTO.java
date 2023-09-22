package cn.anton.internalcommon.request;

import lombok.Data;

/*
 * @author: Anton
 * @create_date: 2023/9/22 17:52
 */
@Data
public class ForecastPriceDTO {
	
	/**
	 * 出发地位置经度
	 */
	private String depLongitude;
	/**
	 * 出发地位置纬度
	 */
	private String depLatitude;
	/**
	 * 目的地经度
	 */
	private String destLongitude;
	/**
	 * 目的地纬度
	 */
	private String destLatitude;
}
