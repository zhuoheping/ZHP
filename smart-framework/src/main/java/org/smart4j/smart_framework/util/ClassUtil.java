package org.smart4j.smart_framework.util;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.smart_framework.annotation.Controller;
import org.smart4j.smart_framework.annotation.Service;
import org.smart4j.smart_framework.helper.ConfigHelper;

/**
 * 类操作工具类  开发一个类加载器
 * @author hp
 *
 */
public final class ClassUtil {
	private  static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);
	
	
	
	private static final Set<Class<?>> CLASS_SET;
	static {
		String basePackage = ConfigHelper.getAppBasePackage();
		CLASS_SET = ClassUtil.getClassSet(basePackage);
	}
	
	/**
	 * 获取应用包下的所有类
	 */
	public static Set<Class<?>> getClassSet(){
		return CLASS_SET;
	}
	
	
	/**
	 * 获取应用包下所有Service类
	 */
	public static Set<Class<?>> getServiceClassSet(){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> cls :CLASS_SET) {
			if(cls.isAnnotationPresent(Service.class)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}
	 
	/**
	 * 获取应用包下所有Controller类
	 */
	public static Set<Class<?>> getControllerClassSet(){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> cls :CLASS_SET) {
			if(cls.isAnnotationPresent(Controller.class)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/**
	 * 获取应用包下所有Bean类(包括:Service ,Controller 等)
	 */
	public static Set<Class<?>> getBeanClassSet(){
		Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
		beanClassSet.addAll(getServiceClassSet());
		beanClassSet.addAll(getControllerClassSet());
		return beanClassSet;
	}
	
	
	/**
	 * 获取类加载器
	 */
	public static ClassLoader getClassLoader() {
		
		return Thread.currentThread().getContextClassLoader();
	}
	
	
	/**
	 * 加载类
	 */
	public static Class<?> loadClass(String className,boolean isInitialized){
		Class<?> cls;
		try {
			cls = Class.forName(className, isInitialized,getClassLoader());	// 为了提高加载类得性能，可以将isInitialized 设置为false
		} catch (ClassNotFoundException e) {
			LOGGER.error("load class failure",e);
			throw new RuntimeException(e);
		}
		
		return cls;
	}
	
	/**
	 * 获取指定包名下的所有类
	 */
	public static Set<Class<?>> getClassSet(String packageName){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		try{
			Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));  //Enumeration 跟 Iterator 作用是否一样
			while(urls.hasMoreElements()){
				URL url = urls.nextElement();
				
				if(url != null){
					String protocol = url.getProtocol();
					if(protocol.equals("file")){
						String packagePath = url.getPath().replaceAll("%20", " ");
						addClass(classSet,packagePath,packageName);
					}else if(protocol.equals("jar")){
						JarURLConnection jarUrlConnection = (JarURLConnection) url.openConnection();
						if(jarUrlConnection != null){
							JarFile jarFile = jarUrlConnection.getJarFile();
							if(jarFile != null){
								Enumeration<JarEntry> jarEntries = jarFile.entries();
								while(jarEntries.hasMoreElements()){
									JarEntry jarEntry = jarEntries.nextElement();
									String jarEntryName = jarEntry.getName();
									if(jarEntryName.endsWith(".class")){
										String className = jarEntryName.substring(0,jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
										doAddClass(classSet,className);
									}
								}
							}
						}
					}
				}
			}
			
			
			
			
		}catch(Exception e){
			LOGGER.error("get class set failure",e);
			throw new RuntimeException(e);
		}
		
		
		
		return classSet;
	}
	
	
	private static void addClass(Set<Class<?>> classSet,String packagePath,String packageName){
		File[] files = new File(packagePath).listFiles(new FileFilter(){

			public boolean accept(File file) {
				return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
			}
		});
		
		for(File file:files){
			String fileName = file.getName();
			if(file.isFile()){
				String className = fileName.substring(0, fileName.lastIndexOf("."));
				if(StringUtil.isNotEmpty(packageName)){
					className = packageName + "."+className;
				}
				doAddClass(classSet,className);
			}else{
				String subPackagePath = fileName;
				if(StringUtil.isNotEmpty(packagePath)){
					subPackagePath = packagePath+"/"+subPackagePath;
				}
				String subPackageName = fileName;
				if(StringUtil.isNotEmpty(packageName)){
					subPackageName = packageName +"."+subPackageName;
				}
				addClass(classSet,subPackagePath,subPackageName);
			}
		}
		
	}
	
	
	
	private static void doAddClass(Set<Class<?>> classSet, String className){
		Class<?> cls = loadClass(className,false);
		classSet.add(cls);
	}
	
	public static void main(String[] args){
		Set<String> set = new HashSet<String>();
		
		System.out.println(set.add("小学生"));
		System.out.println(set.add("大学生"));
		System.out.println(set.add("小学生"));
		
/*		Enumeration<String> its = set.getParameterNames();
		while(its.hasMoreElements()){
			System.out.println(its.nextElement());
		}*/
		
	}

	
	
	
	
}
