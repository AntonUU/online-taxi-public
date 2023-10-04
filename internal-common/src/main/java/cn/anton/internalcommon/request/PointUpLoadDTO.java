package cn.anton.internalcommon.request;

import lombok.Data;

import java.util.List;

/*
 * @author: Anton
 * @create_date: 2023/10/4 16:08
 */
@Data
public class PointUpLoadDTO {
	
	private String tid;
	private String trid;
	private List<Point> points;
	
	@Data
	public static class Point {
		/**
		 * 经纬度坐标  纬度在前经度在后
		 */
		private String location;
		/**
		 * 时间戳
		 */
		private Long locatetime;
	}
	
}
