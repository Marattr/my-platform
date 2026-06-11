package com.ihs.common.util;

public class StringUtil {

    public static boolean isEmpty(String text){
        return text == null || text.trim().isEmpty();
    }
}
