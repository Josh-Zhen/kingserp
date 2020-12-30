package com.moonlit.kingserp.admin.common.utils;

/**
 * @Description: 凱撒加密
 * @Author: Joshua
 * @CreateDate: 2020-02-08 22:40
 * @Version 1.0
 */
public class CaesarCipher {

    /**
     * 加密
     *
     * @param str 輸入值
     * @param k   偏移位
     * @return 編譯碼
     */
    public static String encryption(String str, int k) {
        //加密
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            //如果字符串中的某個字符是小寫字母
            if (c >= 'a' && c <= 'z') {
                //移動key%26位
                c += k % 26;
                if (c < 'a') {
                    //向左超界
                    c += 26;
                }
                if (c > 'z') {
                    //向右超界
                    c -= 26;
                }
            }
            //如果字符串中的某個字符是大寫字母
            else if (c >= 'A' && c <= 'Z') {
                //移動key%26位
                c += k % 26;
                if (c < 'A') {
                    //向左超界
                    c += 26;
                }
                if (c > 'Z') {
                    //向右超界
                    c -= 26;
                }
            }
            //將解密後的字符連成字符串
            string.append(c);
        }
        return string.toString();
    }

    /**
     * 解密
     *
     * @param str 輸入值
     * @param n   偏移位
     * @return 原碼
     */
    public static String decrypt(String str, int n) {
        int k = Integer.parseInt("-" + n);
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            //如果字符串中的某個字符是小寫字母
            if (c >= 'a' && c <= 'z') {
                //移動key%26位
                c += k % 26;
                if (c < 'a') {
                    //向左超界
                    c += 26;
                }
                if (c > 'z') {
                    //向右超界
                    c -= 26;
                }
            }
            //如果字符串中的某個字符是大寫字母
            else if (c >= 'A' && c <= 'Z') {
                //移動key%26位
                c += k % 26;
                if (c < 'A') {
                    //向左超界
                    c += 26;
                }
                if (c > 'Z') {
                    //向右超界
                    c -= 26;
                }
            }
            //將解密後的字符連成字符串
            string.append(c);
        }
        return string.toString();
    }

    public static void main(String[] args) {
        String encryption = encryption("abCz", 1);
        System.out.println(encryption);
        String decrypt = decrypt(encryption, 1);
        System.out.println(decrypt);
    }

}
