package com.moonlit.kingserp.login;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

/**
 * @Description:
 * @Author: Joshua
 * @CreateDate: 2020-06-16 11:26
 * @Version 1.0
 */
public class test {

    public static void main(String[] args) {
//        System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + CommonUtil.getVerificationCode(5));

        String str = "{\n \"titl\":[\"Google\", \"Runoob\", \"Taobao\" ],\n" +
                "\"url\":[ \"Google.html\", \"Runoob.html\", \"Taobao.html\" ]\n }";

        JSONObject jsonObject = JSONObject.parseObject(str);
        System.out.println(jsonObject);
        JSONArray titl = jsonObject.getJSONArray("titl");
        System.out.println(titl);
        JSONArray url = jsonObject.getJSONArray("url");
        System.out.println(url);
        ArrayList<String> list = (ArrayList<String>) JSONObject.parseArray(jsonObject.getJSONArray("titl").toJSONString(), String.class);
        ArrayList<String> list2 = (ArrayList<String>) JSONObject.parseArray(jsonObject.getJSONArray("url").toJSONString(), String.class);

        System.out.println(list);
    }
}
