package com.moonlit.kingserp.admin.common.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @Description: MD5工具类
 * @Author: Joshua
 */
public class MD5Util {
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final String MESSAGE_DIGEST = "MD5";
    private static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * MD5加密
     */
    public static String encrypt(String plainText) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(MESSAGE_DIGEST);
        digest.update(plainText.getBytes(DEFAULT_ENCODING));
        byte[] encryptBytes = digest.digest();
        return new String(encodeHex(encryptBytes));
    }

    /**
     * 鹽加密
     *
     * @return
     */
    private static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }
        return out;
    }

    public static String getKeyedDigest(String strSrc, String key) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(strSrc.getBytes(StandardCharsets.UTF_8));

            StringBuilder ResponseObj = new StringBuilder();
            byte[] temp;
            temp = md5.digest(key.getBytes(StandardCharsets.UTF_8));
            for (byte b : temp) {
                ResponseObj.append(Integer.toHexString((0x000000ff & b) | 0xffffff00).substring(6));
            }
            return ResponseObj.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成 MD5
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    public static String getMessageDigest(String data) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes(StandardCharsets.UTF_8));

            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
            }
        } catch (Exception e) {
            return null;
        }
        return sb.toString().toUpperCase();
    }
}
