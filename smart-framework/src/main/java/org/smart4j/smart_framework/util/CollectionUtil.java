package org.smart4j.smart_framework.util;

import java.util.Map;
import java.util.Set;

/**
 * 集合工具类
 * @author admin
 *
 */
public final class CollectionUtil {

	/**
	 * 判断一个集合是否为空
	 */
	public static boolean isNotEmpty(Map<Class<?>,Object> beanMap){
		return !beanMap.isEmpty();
	}
	
	/**
	 * 判断一个集合是否为空
	 */
	public static boolean isNotEmpty(Set<Class<?>> beanMap){
		return !beanMap.isEmpty();
	}
}
