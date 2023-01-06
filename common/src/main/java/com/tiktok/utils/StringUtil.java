package com.tiktok.utils;


public class StringUtil {

    /**
     * 对多个字符串的判空
     */
    public static boolean isEmpty(String... strs){
        for (String str: strs) {
            if (str != null){
                str = str.trim();
                if ("".equals(str)){
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 对单个字符串进行去除空格操作
     */
    public static String trim(String str){
        if (str != null){
            str = str.trim();
            if ("".equals(str)){
                return null;
            } else {
                return str;
            }
        }
        return null;
    }

}
