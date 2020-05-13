package org.smart4j.smart_framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.smart_framework.bean.Param;


/**
 * 使用ClassHlper 类可以获取所加载的类， 但是无法通过类来实例化对象， 因此我们需要提供一个反射工具类， 让它封装java 反射相关的api
 * 
 * 反射类工具
 * @author admin
 *
 */
public final class ReflectionUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);
	
	/**
	 * 创建实列
	 */
	public static Object newInstance(Class<?> cls){
		Object instance = null;
		try{
			instance = cls.newInstance();
		}catch(Exception e){
			LOGGER.error("new instance failure",e);
			throw new RuntimeException(e);
		}
		
		return instance;
	}
	
	/**
	 *	调用方法 
	 */
	public static Object invokeMethod(Object obj,Method method,Object...objects){
		
		Object result;
		try{
			
			method.setAccessible(true);
			result = method.invoke(obj, objects);
		}catch(Exception e){
			LOGGER.error("invoke method failure",e);
			throw new RuntimeException(e);
		}
		
		return result;
		
	}
	
	/*
	 * 设置成员变量的值
	 */
	public static void setField(Object obj,Field field,Object objects){
		try{
			field.setAccessible(true);
			field.set(obj, objects);
		}catch(Exception e){
			LOGGER.error("set field failure",e);
			throw new RuntimeException(e);
		}
		
	}
}
