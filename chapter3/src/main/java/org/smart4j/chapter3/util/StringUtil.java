package org.smart4j.chapter3.util;

/**
 * StringUtil
 * @description: �ַ���������
 **/
public class StringUtil {

   /**
    * �ж��ַ����Ƿ�Ϊ��
    * @param str
    * @return
    */
   public static boolean isEmpty(String str){
       return str == null || str.trim().length() == 0;
   }

   /**
    * �ж��ַ����Ƿ�ǿ�
    * @param str �����Ϊ�գ��򷵻�true
    * @return
    */
   public static boolean isNotEmpty(String str){
       return !isEmpty(str);
   }
}
