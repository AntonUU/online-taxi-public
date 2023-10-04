package cn.anton.internalcommon.request;

import lombok.Data;

/*
 * @author: Anton
 * @create_date: 2023/10/4 23:43
 */
@Data
public class AroundsearchRequest {
	
	/**
	 * 中心点 格式为：纬度,经度
	 */
	private String center;
	/**
	 * 半径 单位：米
	 */
	private String radius;
	
}
