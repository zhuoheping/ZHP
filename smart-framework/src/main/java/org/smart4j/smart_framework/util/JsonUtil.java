package org.smart4j.smart_framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JsonUtil类用于处理JSON 与POJO 之间的转换， 基于Jackson 实现
 * 
 * Json 工具类
 * 
 * @author admin
 *
 */
public final class JsonUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	/**
	 * 将POJO 转为 JSON
	 */
	public static <T> String toJson(T obj){
		String json;
		try{
			json = OBJECT_MAPPER.writeValueAsString(obj);
		}catch(Exception e){
			LOGGER.error("conver POJO to JSON failure",e);
			throw new RuntimeException(e);
		}
		return json;
	}
	
	/**
	 * JSON 转为POJO
	 */
	public static <T> T fromJson(String json,Class<T> type){
		T pojo;
		try{
			pojo = OBJECT_MAPPER.readValue(json, type);
		}catch(Exception e){
			LOGGER.error("convert JSON to POJO failure",e);
			throw new RuntimeException(e);
		}
		return pojo;
		
	}
	
}
