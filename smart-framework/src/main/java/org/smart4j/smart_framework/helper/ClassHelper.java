package org.smart4j.smart_framework.helper;

import java.util.HashSet;
import java.util.Set;

import org.smart4j.smart_framework.annotation.Controller;
import org.smart4j.smart_framework.annotation.Service;
import org.smart4j.smart_framework.util.ClassUtil;


/**
 * 	由于我们在smart.properties配置文件中指定了应用的基础包名smart.framework.app.base_package，他是整个应用的基础报名， 通过ClassUtil
 *  加载的类都需要基于该基础包名，所以有必要提供一个ClassHelper 助手类，让它分别获取应用包名下的所有类，应用包名下所有Service 类，和所有Controller 类
 *  此外我们可以将带有Controller 注解与Service 注解的类锁产生的随想， 理解为Smart 框架所管理的Bean ，所有有必要在ClassHelper 类中增加一个获取应用包名下所有Bean 
 *  类的方法
 *  
 *  类操作助手类
 * @author admin
 *
 */
public final class ClassHelper {
	/**
	 * 定义类集合（用于存放所加载的类）
	 */
	private static final Set<Class<?>> CLASS_SET;
	
	static{
		String basePackage = ConfigHelper.getAppBasePackage();
		CLASS_SET = ClassUtil.getClassSet(basePackage);
	}
	
	/**
	 * 获取应用包名下所有的类
	 */
	public static Set<Class<?>> getClassSet(){
		return CLASS_SET;
	}
	
	
	/**
	 * 获取应用包名下所有的Service 类
	 */
	public static Set<Class<?>> getServiceClassSet(){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> cls:CLASS_SET){
			if(cls.isAnnotationPresent(Service.class)){
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	
	/**
	 * 获取应用包名下所有Controller 类
	 */
	public static Set<Class<?>> getControllerClassSet(){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> cls:CLASS_SET){
			if(cls.isAnnotationPresent(Controller.class)){
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/**
	 * 获取应用包名下所有Bean 类（包括Service ， Controller 等） 
	 */
	public static Set<Class<?>> getBeanClassSet(){
		Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
		beanClassSet.addAll(getServiceClassSet());
		beanClassSet.addAll(getControllerClassSet());
		return beanClassSet;
	}
}
