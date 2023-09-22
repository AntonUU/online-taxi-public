package cn.anton.internalcommon.response;

import lombok.Data;

import java.math.BigDecimal;

/*
 * @author: Anton
 * @create_date: 2023/9/22 20:34
 */
@Data
public class DirectionResponse {
	
	/**
	 * 距离 单位: 米
	 */
	private Integer distance;
	/**
	 * 时长  单位: 秒
	 */
	private Integer duration;
	/**
	 * 行驶策略
	 */
	private String strategy;
	/**
	 * 过路费
	 */
	private BigDecimal tolls;
	/**
	 * 过路费收费距离  单位：米
	 */
	private Integer tollDistance;
	/**
	 * 红绿灯数量
	 */
	private Integer trafficLights;
	
}
