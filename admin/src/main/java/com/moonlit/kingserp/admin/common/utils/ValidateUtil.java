package com.moonlit.kingserp.admin.common.utils;

import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * 参数校验工具类
 *
 * @author Joshua
 */
public class ValidateUtil {

    /**
     * 检验字符串是否包含大写
     */
    public static boolean isUppercase(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检验字符串是否包含小写
     */
    public static boolean islowercase(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检验字符串是否包涵数字
     */
    public static boolean isDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检验字符串是否包含字母
     */
    public static boolean isLetter(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isLetter(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验参数是否为空
     *
     * @param object
     */
    public static boolean isNull(Object object) {
        return "" == object || object == null;
    }

    /**
     * 校验参数是否为空
     */
    public static boolean isNotBlank(String str) {
        return str != null && str.trim().length() != 0;
    }


    /**
     * 校验整形参数取值大小
     *
     * @param src
     * @param target
     */
    public static boolean isLargerThan(int src, int target) {
        return src > target;
    }

    /**
     * 校验长整型参数取值大小
     *
     * @param src
     * @param target
     */
    public static boolean isLargerThan(long src, long target) {
        return src > target;
    }

    /**
     * 校验浮点参数取值大小
     *
     * @param src
     * @param target
     */
    public static boolean largerThan(float src, float target) {
        return (src > target);
    }

    /**
     * 校验双精度参数取值大小
     *
     * @param src
     * @param target
     */
    public static boolean largerThan(double src, double target) {
        return (src > target);
    }

    /**
     * 校验整形参数取值大小
     *
     * @param src
     * @param target
     */
    public static boolean notLitterThan(int src, int target) {
        return src > target;
    }

    /**
     * 校验长整型参数取值大小
     *
     * @param src
     * @param target
     */
    public static boolean notLitterThan(long src, long target) {
        return src > target;
    }

    /**
     * 校验浮点参数取值大小
     *
     * @param src
     * @param target
     */
    public static boolean notLitterThan(float src, float target) {
        return (src > target);
    }

    /**
     * 校验双精度参数取值大小
     *
     * @param src
     * @param target
     */
    public static boolean notLitterThan(double src, double target) {
        return !(src <= target);
    }

    /**
     * 校验手机号码是否正确
     *
     * @param mobile
     */
    public static boolean isMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        String str = "^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57]|19[0-9])[0-9]{8}$";
        Pattern pattern = Pattern.compile(str);
        return pattern.matcher(mobile).matches();
    }

    /**
     * 校验QQ号码是否正确
     *
     * @param qq
     */
    public static boolean isQQ(String qq) {
        String str = "^[1-9][0-9]{4,9}$";
        Pattern pattern = Pattern.compile(str);
        return pattern.matcher(qq).matches();
    }

    /**
     * 校验email格式是否正确
     *
     * @param email 邮箱
     */
    public static boolean isEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        String str = "^\\w+((-\\w+)|(\\.\\w+))*@[A-Za-z0-9]+(([.\\-])[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        Pattern pattern = Pattern.compile(str);
        return pattern.matcher(email).matches();
    }

    /**
     * 判断金额
     *
     * @param s 金额
     * @return money
     */
    public static String isMoney(String s) {
        return new DecimalFormat("0.00").format(Double.valueOf(s));
    }
}
