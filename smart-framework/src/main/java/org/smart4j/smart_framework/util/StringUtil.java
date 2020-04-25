package org.smart4j.smart_framework.util;

/**
 * StringUtil
 * @description: 字符串工具类
 **/
public final class StringUtil {

   /**
    * 判断字符串是否为空
    * @param str
    * @return
    */
   public static boolean isEmpty(String str){
       return str == null || str.trim().length() == 0;
   }

   /**
    * 判断字符串是否非空
    * @param str 如果不为空，则返回true
    * @return
    */
   public static boolean isNotEmpty(String str){
       return !isEmpty(str);
   }
   
   public static String[] splitString(String str,String sp) {
	   if(str == null || str.length() == 0) {
		   return null;
	   }
	   return str.split(sp);
   }
}
