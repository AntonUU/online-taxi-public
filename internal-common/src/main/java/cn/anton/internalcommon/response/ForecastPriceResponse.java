package cn.anton.internalcommon.response;

import lombok.Data;

/*
 * 预估价格响应Entity
 * @author: Anton
 * @create_date: 2023/9/22 18:05
 */
@Data
public class ForecastPriceResponse {
	
	private Double price;
	private String cityCode;
	private String vehicleType;
	/**
	 * 计价规则类型
	 */
	private String fareType;
	/**
	 * 规则版本号
	 */
	private Integer fareVersion;
	
}
