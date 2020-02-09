package com.moonlit.kingserp.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author shkstart
 * @create 2019-07-23 22:27
 */
public class DateUtils {
    /**
     * 无分隔符日期格式 "yyyyMMddHHmmssSSS"
     */
    public static String DATE_TIME_PATTERN_YYYY_MM_DD_HH_MM_SS_SSS = "yyyyMMddHHmmssSSS";

    /**
     * 获取带格式的日期字符串
     *
     * @param length
     * @return
     */
    public static String getDateStr(Integer length) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String ResponseObj = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        if (length < 1 || length > 17) {
            return ResponseObj;
        } else {
            return ResponseObj.substring(0, length);
        }
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 日期比较，如果 s<=e 返回true 否则返回false
     *
     * @param s
     * @param e
     * @return
     */
    public static boolean compareDate(String s, String e) throws ParseException {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        if (fmt.parse(s) == null || fmt.parse(e) == null) {
            return false;
        }
        return e.compareTo(s) >= 0;
    }

    public static void main(String[] args) throws ParseException {
        //true
        System.out.println(compareDate("2019-09-10", "2019-10-10"));
    }
}
