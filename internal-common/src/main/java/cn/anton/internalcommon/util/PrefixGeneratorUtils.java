package cn.anton.internalcommon.util;

/*
 * 前缀工具类
 * @author: Anton
 * @create_date: 2023/9/7 22:17
 */
public class PrefixGeneratorUtils {
	
	private static final String TOKEN_PREFIX = "token-";
	
	// 黑名单设备号
	private static final String BLACK_DEVICE_CODE_PREFIX = "black-device-";
	
	
	public static String generatorTokenKey(String passengerPhone, String identity, String tokenType){
		return TOKEN_PREFIX + passengerPhone + "-" + identity + "-" + tokenType;
	}
	
	public static String generatorBlackDeviceCodeKey(String deviceCode){
		return BLACK_DEVICE_CODE_PREFIX + deviceCode;
	}
}
