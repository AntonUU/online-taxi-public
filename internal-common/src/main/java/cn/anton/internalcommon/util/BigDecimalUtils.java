package cn.anton.internalcommon.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
 * @author: Anton
 * @create_date: 2023/9/24 18:25
 */
public class BigDecimalUtils {
	
	/**
	 * 加法
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static Double add(Double b1, Double b2) {
		return BigDecimal.valueOf(b1).add(BigDecimal.valueOf(b2)).doubleValue();
	}
	
	/**
	 * 除法
	 * @return
	 */
	public static Double divide(Double b1, Double b2) throws IllegalAccessException {
		if (b2 <= 0)
			throw new IllegalAccessException("除数非法");
		return BigDecimal.valueOf(b1).divide(BigDecimal.valueOf(b2), 2, RoundingMode.HALF_UP).doubleValue();
	}
	
	public static Double divide(Double b1, Integer b2) throws IllegalAccessException {
		if (b2 <= 0)
			throw new IllegalAccessException("除数非法");
		return BigDecimal.valueOf(b1).divide(BigDecimal.valueOf(b2), 2, RoundingMode.HALF_UP).doubleValue();
	}
	
	public static Double divide(Integer b1, Integer b2) throws IllegalAccessException {
		if (b2 <= 0)
			throw new IllegalAccessException("除数非法");
		return BigDecimal.valueOf(b1).divide(BigDecimal.valueOf(b2), 2, RoundingMode.HALF_UP).doubleValue();
	}
	
	public static Double subtract(Double b1, Double b2){
		return BigDecimal.valueOf(b1).subtract(BigDecimal.valueOf(b2)).doubleValue();
	}
	
	public static Double subtract(Double b1, Integer b2){
		return BigDecimal.valueOf(b1).subtract(BigDecimal.valueOf(b2)).doubleValue();
	}
	
	public static Double multiply(Double b1, Double b2) {
		return BigDecimal.valueOf(b1).multiply(BigDecimal.valueOf(b2)).doubleValue();
	}
	
	public static Double setScale(Double b, int num){
		return new BigDecimal(b).setScale(num, RoundingMode.HALF_UP).doubleValue();
	}
	
	
}
