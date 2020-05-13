package org.smart4j.chapter3.util;

import java.util.Collection;

/**
 * ת�Ͳ���������
 */
public final class CastUtil {
 
    /**
     * תΪ String ��
     */
    public static String castString(Object obj) {
        return CastUtil.castString(obj, "");
    }
 
    /**
     * תΪ String �ͣ��ṩĬ��ֵ��
     */
    public static String castString(Object obj, String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }
 
    /**
     * תΪ double ��
     */
    public static double castDouble(Object obj) {
        return CastUtil.castDouble(obj, 0);
    }
 
    /**
     * תΪ double �ͣ��ṩĬ��ֵ��
     */
    public static double castDouble(Object obj, double defaultValue) {
        double doubleValue = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    doubleValue = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }
 
    /**
     * תΪ long ��
     */
    public static long castLong(Object obj) {
        return CastUtil.castLong(obj, 0);
    }
 
    /**
     * תΪ long �ͣ��ṩĬ��ֵ��
     */
    public static long castLong(Object obj, long defaultValue) {
        long longValue = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    longValue = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }
 
    /**
     * תΪ int ��
     */
    public static int castInt(Object obj) {
        return CastUtil.castInt(obj, 0);
    }
 
    /**
     * תΪ int �ͣ��ṩĬ��ֵ��
     */
    public static int castInt(Object obj, int defaultValue) {
        int intValue = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    intValue = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }
 
    /**
     * תΪ boolean ��
     */
    public static boolean castBoolean(Object obj) {
        return CastUtil.castBoolean(obj, false);
    }
 
    /**
     * תΪ boolean �ͣ��ṩĬ��ֵ��
     */
    public static boolean castBoolean(Object obj, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if (obj != null) {
            booleanValue = Boolean.parseBoolean(castString(obj));
        }
        return booleanValue;
    }
    
	public static void main(String[] args) {

	}
}
