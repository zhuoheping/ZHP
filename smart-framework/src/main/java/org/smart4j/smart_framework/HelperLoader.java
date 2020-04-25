package org.smart4j.smart_framework;

import org.smart4j.smart_framework.helper.BeanHelper;
import org.smart4j.smart_framework.helper.ClassHelper;
import org.smart4j.smart_framework.helper.ControllerHelper;
import org.smart4j.smart_framework.helper.IocHelper;
import org.smart4j.smart_framework.util.ClassUtil;

/**
 * 我们需要 一个入口程序来加载  ClassHelper BeanHelper IocHelper ControllerHelper 类
 * 实际上就是加载它们的静态块
 * 
 * 加载相应的Helper 类
 * @author admin
 *
 */
public class HelperLoader {
	
	public static void init(){
		Class<?>[] classList = {
			ClassHelper.class,	//获取指定包名下所有的class
			BeanHelper.class,	//指定包名下Controller Service class 创建对象实例
			IocHelper.class,	//获取当前所有class 下字段， 判断是否有ioc 注解， 有的话， 取出实例注入
			ControllerHelper.class	//封装Controller 实例和方法， key 为请求路径和请求方法， value Controller 实例 和 请求方法
		};
		for(Class<?> cls:classList){
			ClassUtil.loadClass(cls.getName(),false);
		}
	}
}
