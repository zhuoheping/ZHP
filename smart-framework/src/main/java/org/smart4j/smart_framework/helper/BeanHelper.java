package org.smart4j.smart_framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.smart4j.smart_framework.util.ReflectionUtil;

/**
 * 
 * 我们需要获取所有被Smart 框架管理的Bean 类， 此时需要调用ClassHelper 类的getBeanClassSet方法，随后需要循环调用RefiectionUtil 类的newInstance 方法，
 * 根据类来实例化该对象，最后将每次创建的对象存放在一个金泰的Map<Class<?>,Object> 中， 我们需要随时获取Map
 * 
 * 
 * bean 助手类
 * @author admin
 *
 */
public final class BeanHelper {

	/**
	 * 定义 Bean 映射（用于存放Bean 类 与Bean 实例的映射关系）
	 */
	private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>,Object>();
	
	
	static{
		Set<Class<?>>  beanClassSet = ClassHelper.getBeanClassSet();
		for(Class<?> beanClass:beanClassSet){
			Object obj = ReflectionUtil.newInstance(beanClass);
			BEAN_MAP.put(beanClass, obj);
		}
	}
	
	/**
	 * 获取Bean 映射
	 */
	
	public static Map<Class<?>,Object>  getBeanMap(){
		return BEAN_MAP;
	}
	
	/**
	 * 获取Bean 实例
	 * @return 
	 */
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> cls){
		
		if(!BEAN_MAP.containsKey(cls)){
			throw new RuntimeException("can not get bean by class:"+cls);
		}
		
		return (T) BEAN_MAP.get(cls);
	}
}
