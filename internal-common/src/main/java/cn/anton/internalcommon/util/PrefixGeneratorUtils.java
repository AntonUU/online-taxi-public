package cn.anton.internalcommon.util;

/*
 * 前缀工具类
 * @author: Anton
 * @create_date: 2023/9/7 22:17
 */
public class PrefixGeneratorUtils {
	
	private static final String TOKEN_PREFIX = "token-";
	
	
	public static String generatorTokenKey(String passengerPhone, String identity){
		System.out.println(passengerPhone);
		System.out.println(identity);
		return TOKEN_PREFIX + passengerPhone + "-" + identity;
	}
}
