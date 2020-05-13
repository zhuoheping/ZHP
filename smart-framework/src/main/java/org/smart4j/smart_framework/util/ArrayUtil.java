package org.smart4j.smart_framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 数组工具类
 * @author admin
 *
 */
public final class ArrayUtil {

	/**
	 * 判断数组是否为空
	 */
	public static boolean isNotEmpty(Object[] array){
		return !ArrayUtils.isEmpty(array);
	}
	
	/**
	 * 判断数组是否为空
	 */
	public static boolean isEmpty(Object[] array){
		return ArrayUtils.isEmpty(array);
	}
}
