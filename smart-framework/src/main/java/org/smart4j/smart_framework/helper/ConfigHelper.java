package org.smart4j.smart_framework.helper;

import java.util.Properties;

import org.smart4j.smart_framework.ConfigConstant;
import org.smart4j.smart_framework.util.PropsUtil;

/**
 * 属性文件助手类
 * @author hp
 *
 */
public final class ConfigHelper {

	private static Properties  CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE); 
	
	/**
	 * 获取JDBC驱动
	 * @return
	 */
	public static String getJdbcDriver() {
		
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
	}
	
	/**
	 * 获取 JDBC url
	 * @return
	 */
	public static String getJdbcUrl() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
	}
	
	/**
	 * 获取 JDBC 用户名
	 * @return
	 */
	public static String getJdbcUsername() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
	}
	
	/**
	 * 获取 JDBC 密码
	 * @return
	 */
	public static String getJdbcPassword() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
	}
	
	/**
	 * 获取 应用基础包名
	 * @return
	 */
	public static String getAppBasePackage() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
	}
	
	
	/**
	 * 获取 应用jsp 路径
	 * @return
	 */
	public static String getAppJspPath() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH,"/WEB-INF/view/");
	}
	
	/**
	 * 获取应用静态资源路径
	 */
	public static String getAppAssetPath() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH,"/asset/");
	}
}

