package org.smart4j.smart_framework.bean;

import java.lang.reflect.Method;

/**
 * 封装 Action
 * @author admin
 *
 */
public class Handler {

	/**
	 * Contriller 类
	 */
	private Class<?> controllerClass;
	
	/**
	 * Aciotn 方法
	 */
	private Method actionMethod;
	
	public Handler(Class<?> controllerClass,Method actionMethod){
		this.controllerClass = controllerClass;
		this.actionMethod = actionMethod;
	}
	
	public Class<?> getControllerClass(){
		return controllerClass;
	}
	
	public Method getActionMethod(){
		return actionMethod;
	}
	
	public static void main(String[] args) {
		Object[] [] obj= {{},{},{}};
	}
}
