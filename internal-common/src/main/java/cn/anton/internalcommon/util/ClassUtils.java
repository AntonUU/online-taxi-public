package cn.anton.internalcommon.util;

import cn.anton.internalcommon.dao.Car;

import java.lang.reflect.Field;

/*
 * @author: Anton
 * @create_date: 2023/10/1 08:06
 */
public class ClassUtils {
	
	/**
	 * 打印类字段名称
	 */
	public static void printFieldName(Class c) {
		Field[] declaredFields = c.getDeclaredFields();
		for (int i = 0; i < declaredFields.length; i++) {
			System.out.println(declaredFields[i].getName());
		}
	}
	
}
