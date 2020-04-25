package org.smart4j.smart_framework.bean;

import java.util.Map;

import org.smart4j.smart_framework.util.CastUtil;

/**
 * 请求参数对象
 * @author admin
 *
 */
public class Param {

	private Map<String,Object> paramMap;
	
	public Param(Map<String,Object> paramMap){
		this.paramMap = paramMap;
	}
	
	/**
	 * 根据参数名获取Long 型参数值
	 */
	public long getLong(String name){
		return CastUtil.castLong(paramMap.get("name"));
	}
	
	/**
	 * 获取所有字段信息
	 */
	public Map<String,Object> getMap(){
		return paramMap;
	}
	
}
