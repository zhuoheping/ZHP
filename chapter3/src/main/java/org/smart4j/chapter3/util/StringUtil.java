package org.smart4j.chapter3.util;

/**
 * StringUtil
 * @description: 字符串工具类
 **/
public class StringUtil {

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
}
