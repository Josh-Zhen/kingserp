package com.moonlit.kingserp.common.util;

/**
 * @author Joshua
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        return null == str || 0 == str.trim().length();
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }


}
