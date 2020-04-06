package com.moonlit.kingserp.common.util;

import org.apache.commons.lang3.ArrayUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 公共工具类
 *
 * @author Joshua
 */
public class CommonUtil {

    /**
     * 生成随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            // [0,62)
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成N位纯数字验证码
     *
     * @return
     */
    public static String getVerificationCode(int n) {
        final Random random = new Random();
        StringBuilder verificationCode = new StringBuilder();
        for (int i = 0; i < n; i++) {
            verificationCode.append(random.nextInt(10));
        }
        return verificationCode.toString();
    }

    public static void main(String[] args) {
        String ss = getVerificationCode(11);
        System.out.println(ss);
    }

    /**
     * 生产订单号
     *
     * @param prefix A:夺宝订单;S:商城订单;C:充值订单;H:撮合系统发货单单号;I:撮合系统收货单单号
     * @return
     */
    public static String getOrderNum(String prefix) {
        String time = String.valueOf(System.currentTimeMillis());
        return prefix + time + time.subSequence(2, 6) + getVerificationCode(1);
    }

    /**
     * 生成随机字符串(比方法getRandomString()快)
     *
     * @param length
     * @return
     */
    public static String getRandomString2(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(3);
            long ResponseObj = 0;

            switch (number) {
                case 0:
                    ResponseObj = Math.round(Math.random() * 25 + 65);
                    sb.append((char) ResponseObj);
                    break;
                case 1:
                    ResponseObj = Math.round(Math.random() * 25 + 97);
                    sb.append((char) ResponseObj);
                    break;
                case 2:
                    sb.append(new Random().nextInt(10));
                    break;
                default:
            }
        }
        return sb.toString();
    }

    /**
     * 对象转string
     *
     * @param obj
     * @return
     */
    public static String ObjToString(Object obj) {
        if (null == obj) {
            return null;
        }
        return obj.toString();
    }

    /**
     * 对象转int
     *
     * @param obj
     * @return
     */
    public static int ObjToInt(Object obj) {
        if (null == obj) {
            return 0;
        }
        return Integer.parseInt(ObjToString(obj));
    }

    /**
     * 对象转long
     *
     * @param obj
     * @return
     */
    public static Long objToLong(Object obj) {
        if (null == obj) {
            return null;
        }
        return Long.parseLong(ObjToString(obj));
    }

    /**
     * 生成UUID
     *
     * @return
     */
    public static String retrieveUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 图片详情前缀
     *
     * @return
     */
    public static String getContentPrefix() {
        return "<style>img {height: auto;width: auto\\9;width:100%;}</style>";
    }

    /**
     * 两个整数相除保留两位小数,不足补为1
     *
     * @param fValue
     * @param tValue
     * @return
     */
    public static String getFloat(int fValue, int tValue) {
        if (0 == fValue) {
            return "0";
        } else {
            float number = (float) fValue / tValue;
            int ret = new Float(number * 100).intValue();
            if (0 == ret) {
                ret = 1;
            }
            return ret + "";
        }
    }

    /**
     * 手机隐藏中间4位
     *
     * @param str
     * @return
     */
    public static String getNewMobile(String str) {
        if (ValidateUtil.isMobile(str)) {
            return str.substring(0, 3) + "****" + str.substring(7);
        }
        return str;
    }

    /**
     * 判断数组是否包含字符串
     *
     * @param filterName
     * @param name
     * @return
     */
    public static boolean isContains(String filterName, String name) {
        String[] names = filterName.split(",");
        return ArrayUtils.contains(names, name);
    }

    /**
     * 判断字符串包含值得长度
     *
     * @param filterName
     * @param separator  分隔符
     * @return
     */
    public static int getSeparatorNumber(String filterName, String separator) {
        if (filterName == null || "".equals(filterName)) {
            return 0;
        }
        String[] names = filterName.split(separator);
        return names.length;
    }

    /**
     * 获取字符串包含值集合
     *
     * @param obj
     * @return
     */
    public static List<Integer> getIdsList(Object obj) {
        List<Integer> ids = new ArrayList<>();
        if (null != obj) {
            String[] strings = obj.toString().split(",");
            for (String str : strings) {
                if (str != null && !"".equals(str)) {
                    ids.add(Integer.parseInt(str));
                }
            }
        }
        return ids;
    }

    /**
     * 多张图片返回集合
     *
     * @param obj
     * @return
     */
    public static List<String> getImgList(Object obj) {
        List<String> images = new ArrayList<>();
        if (null != obj) {
            String[] imgArray = obj.toString().split(",");
            images = Arrays.asList(imgArray);

        }
        return images;
    }

    /**
     * map按key排序
     *
     * @param map
     * @return
     */
    public static Map<String, Object> sortMap(Map<String, Object> map) {
        SortedMap<String, Object> sort = new TreeMap<String, Object>(map);
        return sort;
    }

    public static float floatMul(float num1, float num2) {
        BigDecimal b1 = new BigDecimal(Double.toString(num1));
        BigDecimal b2 = new BigDecimal(Double.toString(num2));
        return b1.multiply(b2).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 生成会员卡号
     *
     * @return
     */
    public static String getMemberCode() {
        String time = DateUtil.getCurDate().replace("-", "");
        return time + getVerificationCode(5);
    }

    /**
     * 生成订单的编号order_sn
     *
     * @return
     */
    public static String generateOrderNumber() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        String timeStr = DateUtils.format(cal.getTime(), DateUtils.DATE_TIME_PATTERN_YYYY_MM_DD_HH_MM_SS_SSS);
        return timeStr + getRandomNum(6);
    }

    /**
     * 获取随机字符串
     *
     * @param num
     * @return
     */
    public static String getRandomNum(Integer num) {
        String base = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
