package org.smart4j.smart_framework.bean;

/**
 * 返回数据对象
 * @author admin
 *
 */
public class Data {
	
	/**
	 * 模型数据
	 */
	private Object model;
	
	public Data(Object model){
		this.model = model;
	}
	
	public Object getModel(){
		return model;
	}
}
