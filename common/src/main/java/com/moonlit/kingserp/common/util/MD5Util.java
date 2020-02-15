package com.moonlit.kingserp.common.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
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
        String cipherText = new String(encodeHex(encryptBytes));
        return cipherText;
    }

    /**
     * 鹽加密
     *
     * @param password
     * @param salf
     * @return
     */
    public static String getMd5insalf(String password, String salf) {
        String md5 = new Md5Hash(password, ByteSource.Util.bytes(salf.getBytes())).toString();
        return md5;
    }

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
            md5.update(strSrc.getBytes("UTF8"));

            String ResponseObj = "";
            byte[] temp;
            temp = md5.digest(key.getBytes("UTF8"));
            for (int i = 0; i < temp.length; i++) {
                ResponseObj += Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
            }
            return ResponseObj;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
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
            byte[] array = md.digest(data.getBytes("UTF-8"));

            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
        } catch (Exception e) {
            return null;
        }
        return sb.toString().toUpperCase();
    }
}
