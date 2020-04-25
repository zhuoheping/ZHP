package org.smart4j.smart_framework.helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import org.smart4j.smart_framework.annotation.Inject;
import org.smart4j.smart_framework.util.ArrayUtil;
import org.smart4j.smart_framework.util.CollectionUtil;
import org.smart4j.smart_framework.util.ReflectionUtil;


/**
 * 通过BeanHelper 获取所有Bean Map（是一个Map<Class<?>,Object> 结构，记录了类与对象的映射关系） 然后遍历这个映射关系，分别取出Bean 类 与
 * Bean 实例。 进而通过反射获取类中的所有成员变量，继续遍历这些成员变量，在循环中判断当前成员变量是否带有Inject 注解，若带有该注解， 则从Bean Map 中根据
 * Bean 类取出Bean 实例，最后是通过 ReflectionUtil#setField方法来修改当前成员变量的值。
 * 
 * 依赖注入助手类
 *  
 * @author admin
 *
 */
public final class IocHelper {

	
	static{
		//获取所有的Bean 类与Bean 实例之间的映射关系（简称Bean Map）
		Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
		if(CollectionUtil.isNotEmpty(beanMap)){
			//遍历Bean Map
			for(Map.Entry<Class<?>,Object> beanEntry:beanMap.entrySet()){
				//从BeanMap 中获取Bean 类 与Bean 的实例
				Class<?> beanClass = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();
				//获取Bean 类定义的所有成员变量（简称Bean Field）
				Field[] beanFields = beanClass.getDeclaredFields();
				if(ArrayUtil.isNotEmpty(beanFields)){
					//遍历 Bean Field
					for(Field beanField:beanFields){
						//遍历 当前 Bean Field 中是否带有Inject 注解
						if(beanField.isAnnotationPresent(Inject.class)){
							// 在Bean Map 中获取 Bean Field 对应的实例
							Class<?> beanFieldClass=beanField.getType();
							Object beanFieldInstance = beanMap.get(beanFieldClass);
							if(beanFieldInstance != null){
								//通过反射初始化 BeanField 的值
								ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
							}
						}
					}
				}
			}
		}
		
	}
	
	
	
	
}
