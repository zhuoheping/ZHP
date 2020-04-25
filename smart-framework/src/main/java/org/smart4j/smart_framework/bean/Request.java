package org.smart4j.smart_framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 通过其它类 可以获取Action 注解中的请求表达式， 进而获取请求方法与请求路径， 封装一个请求对象（Request） 与处理对象（Handler），最后将Request 与Handler 建立一个映射关系，
 * 放入一个Action Map 中，并提供一个可根据请求方法与请求路径获取处理对象的方法。
 * @author admin
 *
 */
public class Request {

	/**
	 * 请求方法
	 */
	private String requestMethod;

	/**
	 * 请求路径
	 */
	private String requestPath;
	
	
	public Request(String requestMethod,String requestPath){
		this.requestMethod = requestMethod;
		this.requestPath = requestPath;
	}


	public String getRequestMethod() {
		return requestMethod;
	}




	public String getRequestPath() {
		return requestPath;
	}


	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}


	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this,obj);
	}


	
	
}
