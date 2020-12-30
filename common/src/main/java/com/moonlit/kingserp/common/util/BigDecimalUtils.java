package com.moonlit.kingserp.common.util;

import com.sun.istack.internal.Nullable;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @Description: 高精度科學計算工具類
 * @Author: Joshua
 * @CreateDate: 2020/12/29 22:59
 * @Version 1.0
 */
@Slf4j
public class BigDecimalUtils {

    /**
     * 加法運算
     *
     * @param v1    加數
     * @param v2    被加數
     * @param scale 保留幾位小數(會四捨五入，可為null)
     * @return sum
     */
    public static String add(String v1, String v2, @Nullable Integer scale) {
        BigDecimal sum = new BigDecimal(v1).add(new BigDecimal(v2));
        if (scale != null) {
            sum = round(sum, scale);
        }
        return sum.toString();
    }

    /**
     * 加法運算
     *
     * @param v1    加數
     * @param v2    被加數
     * @param scale 保留幾位小數(會四捨五入，可為null)
     * @return sum
     */
    public static double add(double v1, double v2, @Nullable Integer scale) {
        BigDecimal sum = new BigDecimal(v1).add(new BigDecimal(v2));
        if (scale != null) {
            sum = round(sum, scale);
        }
        return sum.doubleValue();
    }

    /**
     * 减法運算
     *
     * @param v1    被减數
     * @param v2    减數
     * @param scale 保留幾位小數(會四捨五入，可為null)
     * @return 兩數差
     */
    public static String subtract(String v1, String v2, @Nullable Integer scale) {
        BigDecimal difference = new BigDecimal(v1).subtract(new BigDecimal(v2));
        if (scale != null) {
            difference = round(difference, scale);
        }
        return difference.toString();
    }

    /**
     * 减法運算
     *
     * @param v1    被减數
     * @param v2    减數
     * @param scale 保留幾位小數(會四捨五入，可為null)
     * @return 兩數差
     */
    public static double subtract(double v1, double v2, @Nullable Integer scale) {
        BigDecimal difference = new BigDecimal(v1).subtract(new BigDecimal(v2));
        if (scale != null) {
            difference = round(difference, scale);
        }
        return difference.doubleValue();
    }

    /**
     * 乘法運算
     *
     * @param v1    被乘數
     * @param v2    乘數
     * @param scale 保留幾位小數(會四捨五入，可為null)
     * @return 兩數积
     */
    public static String multiply(String v1, String v2, @Nullable Integer scale) {
        BigDecimal accumulated = new BigDecimal(v1).multiply(new BigDecimal(v2));
        if (scale != null) {
            round(accumulated, scale);
        }
        return accumulated.toString();
    }

    /**
     * 乘法運算
     *
     * @param v1    被乘數
     * @param v2    乘數
     * @param scale 保留幾位小數(會四捨五入，可為null)
     * @return 兩數积
     */
    public static double multiply(double v1, double v2, @Nullable Integer scale) {
        BigDecimal accumulated = new BigDecimal(v1).multiply(new BigDecimal(v2));
        if (scale != null) {
            round(accumulated, scale);
        }
        return accumulated.doubleValue();
    }

    /**
     * 簡單的除法運算，如果除不盡會保留小數點後4位
     *
     * @param v1 被除數
     * @param v2 除數
     * @return 两个數的商
     */
    public static String divide(String v1, String v2) {
        return divide(v1, v2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 簡單的除法運算，如果除不盡會保留小數點後4位
     *
     * @param v1 被除數
     * @param v2 除數
     * @return 两个數的商
     */
    public static double divide(double v1, double v2) {
        return divide(v1, v2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 精度除法運算
     *
     * @param v1    被除數
     * @param v2    除數
     * @param scale 保留幾位小數(會四捨五入)
     * @return 两个數的商
     */
    public static String divide(String v1, String v2, Integer scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return new BigDecimal(v1).divide(new BigDecimal(v2), scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 精度除法運算
     *
     * @param v1    被除數
     * @param v2    除數
     * @param scale 保留幾位小數(會四捨五入)
     * @return 两个數的商
     */
    public static double divide(double v1, double v2, Integer scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return new BigDecimal(v1).divide(new BigDecimal(v2), scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 取餘數
     *
     * @param v1    被除數
     * @param v2    除數
     * @param scale 小数点后保留几位
     * @return 餘數
     */
    public static String remainder(String v1, String v2, Integer scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return new BigDecimal(v1).remainder(new BigDecimal(v2)).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 值比較
     *
     * @param v1 被比較數
     * @param v2 比較數
     * @return 如果v1 大于v2 则 返回true 否则false
     */
    public static boolean compare(String v1, String v2) {
        boolean res;
        res = new BigDecimal(v1).compareTo(new BigDecimal(v2)) > 0;
        return res;
    }

    /**
     * 四捨五入處理
     *
     * @param v     需要四捨五入的值
     * @param scale 保留幾位小數
     * @return 四捨五入結果
     */
    public static BigDecimal round(BigDecimal v, Integer scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return v.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 四捨五入處理
     *
     * @param v     需要四捨五入的值
     * @param scale 保留幾位小數
     * @return 四捨五入結果
     */
    public static String round(String v, Integer scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return new BigDecimal(v).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

}
