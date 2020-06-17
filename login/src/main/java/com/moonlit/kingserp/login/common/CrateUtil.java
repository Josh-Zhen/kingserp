package com.moonlit.kingserp.login.common;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayMarketingCardFormtemplateSetRequest;
import com.alipay.api.request.AlipayMarketingCardTemplateCreateRequest;
import com.alipay.api.request.AlipayMarketingCardTemplateModifyRequest;
import com.alipay.api.response.AlipayMarketingCardFormtemplateSetResponse;
import com.alipay.api.response.AlipayMarketingCardTemplateCreateResponse;
import com.alipay.api.response.AlipayMarketingCardTemplateModifyResponse;
import com.moonlit.kingserp.common.util.ChineseToEnUtil;
import com.moonlit.kingserp.common.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @Description: 會員卡相關遠端接口
 * @Author: Joshua
 * @CreateDate: 2020-06-16 10:24
 * @Version 1.0
 */
@Slf4j
public class CrateUtil {

    private static AlipayClient alipayClient = new DefaultAlipayClient(AliCreateConfig.gatewayUrl, AliCreateConfig.app_id, AliCreateConfig.merchant_private_key, AliCreateConfig.format, AliCreateConfig.charset, AliCreateConfig.alipay_public_key, AliCreateConfig.sign_type);

    /**
     * 會員卡模板創建
     *
     * @param title        錢包端顯示名稱（如：花唄聯名卡）
     * @param logo         logo圖片地址
     * @param backgroundId 背景圖片地址
     * @param customizeArr 自定義入口
     * @param phone        商家聯係方式
     * @param sourceAppId  商家token
     * @throws AlipayApiException
     */
    public String crateModel(String title, String logo, String backgroundId, HashMap<String, ArrayList<String>> customizeArr, String phone, String sourceAppId) throws AlipayApiException {
        // 初始化
        AlipayMarketingCardTemplateCreateRequest request = new AlipayMarketingCardTemplateCreateRequest();
        // 幫用戶創建模板需傳入該用戶token
        request.putOtherTextParam("app_auth_token", sourceAppId);
        // 入口標題
        ArrayList<String> titles = customizeArr.get("title");
        // 鏈接地址
        ArrayList<String> urls = customizeArr.get("Url");

        // 存放模板參數
        request.setBizContent("{"
                + "\"request_id\":\"" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + CommonUtil.getVerificationCode(5) + "\","
                + "\"card_type\":\"OUT_MEMBER_CARD\","
                + "\"biz_no_prefix\":\"" + new SimpleDateFormat("yyyyMMddHHmmss") + "\","
                + "\"biz_no_suffix_len\":\"10\","
                + "\"write_off_type\":\"qrcode\","
                + "\"template_style_info\":{"
                + "\"card_show_name\":\"" + title + "\","
                + "\"logo_id\":\"" + logo + "\","
                + "\"background_id\":\"" + backgroundId + "\","
                + "\"bg_color\":\"rgb(55,112,179)\","
                // 設置會員卡權益列表
//                + "\"template_benefit_info\":[{\"title\":\"消费即折扣\","
//                + "\"benefit_desc\":[\"消费即折扣\",\"会员日7折扣\"],"
                // 開始與結束時間
//                + "\"start_date\":\"2016-07-18 15:17:23\",\"end_date\":\"2016-07-34 12:12:12\"}],"
                // 自定義列表
                + "\"column_info_list\":[{"
                + "{\"code\":\"" + ChineseToEnUtil.getPingYin(titles.get(0)) + "\",\"\\\"operate_type\\\":\\\"openWeb\\\",\"title\":\"" + titles.get(0) + "\",\"value\":\"\"},"
                + "{\"code\":\"" + ChineseToEnUtil.getPingYin(titles.get(1)) + "\",\"\\\"operate_type\\\":\\\"openWeb\\\",\"title\":\"" + titles.get(1) + "\",\"value\":\"\"},"
                + "{\"code\":\"" + ChineseToEnUtil.getPingYin(titles.get(2)) + "\",\"\\\"operate_type\\\":\\\"openWeb\\\",\"title\":\"" + titles.get(2) + "\",\"value\":\"\"},"
                + "{\"code\":\"" + ChineseToEnUtil.getPingYin(titles.get(3)) + "\",\"\\\"operate_type\\\":\\\"openWeb\\\",\"title\":\"" + titles.get(3) + "\",\"value\":\"\"},"
                + "{\"code\":\"TELEPHONE\",\"\\\"operate_type\\\":\\\"staticinfo\\\",\"title\":\"联系商家\",\"value\":\"" + phone + "\"},"
                + "],"
                // 會員卡信息規則
                + "\"field_rule_list\":[{"
                // 開卡日期與過期日期
                + "\"field_name\":\"OpenDate\",\"rule_name\":\"DATE_IN_FUTURE\",\"rule_value\":\"" + new Date() + "\""
                + "}],"
                + "}");
        AlipayMarketingCardTemplateCreateResponse response = null;
        try {
            response = alipayClient.execute(request);
            System.out.println(response.getBody());
            //打印支付宝卡模板ID
            System.out.println(response.getTemplateId());
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assert response != null;
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return response.getBody();
    }

    /**
     * 更新模板
     *
     * @param title        錢包端顯示名稱（如：花唄聯名卡）
     * @param logo         logo圖片地址
     * @param backgroundId 背景圖片地址
     * @param customizeArr 自定義入口
     * @param phone        商家聯係方式
     * @param sourceAppId  商家token
     * @param templateId   模板卡id
     * @throws AlipayApiException
     */
    public String updataCrate(String title, String logo, String backgroundId, HashMap<String, ArrayList<String>> customizeArr, String phone, String sourceAppId, String templateId) throws AlipayApiException {
        // 初始化
        AlipayMarketingCardTemplateModifyRequest request = new AlipayMarketingCardTemplateModifyRequest();
        // 幫用戶創建模板需傳入該用戶token
        request.putOtherTextParam("app_auth_token", sourceAppId);
        // 入口標題
        ArrayList<String> titles = customizeArr.get("title");
        // 鏈接地址
        ArrayList<String> urls = customizeArr.get("Url");

        // 存放模板參數
        request.setBizContent("{"
                + "\"request_id\":\"" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + CommonUtil.getVerificationCode(5) + "\","
                + "\"template_id\":\"" + templateId + "\","
                + "\"biz_no_prefix\":\"" + new SimpleDateFormat("yyyyMMddHHmmss") + "\","
                + "\"biz_no_suffix_len\":\"10\","
                + "\"write_off_type\":\"qrcode\","
                + "\"template_style_info\":{"
                + "\"card_show_name\":\"" + title + "\","
                + "\"logo_id\":\"" + logo + "\","
                + "\"background_id\":\"" + backgroundId + "\","
                + "\"bg_color\":\"rgb(55,112,179)\","
                // 設置會員卡權益列表
//                + "\"template_benefit_info\":[{\"title\":\"消费即折扣\","
//                + "\"benefit_desc\":[\"消费即折扣\",\"会员日7折扣\"],"
                // 開始與結束時間
//                + "\"start_date\":\"2016-07-18 15:17:23\",\"end_date\":\"2016-07-34 12:12:12\"}],"
                // 自定義列表
                + "\"column_info_list\":[{"
                + "{\"code\":\"" + ChineseToEnUtil.getPingYin(titles.get(0)) + "\",\"\\\"operate_type\\\":\\\"openWeb\\\",\"title\":\"" + titles.get(0) + "\",\"value\":\"\"},"
                + "{\"code\":\"" + ChineseToEnUtil.getPingYin(titles.get(1)) + "\",\"\\\"operate_type\\\":\\\"openWeb\\\",\"title\":\"" + titles.get(1) + "\",\"value\":\"\"},"
                + "{\"code\":\"" + ChineseToEnUtil.getPingYin(titles.get(2)) + "\",\"\\\"operate_type\\\":\\\"openWeb\\\",\"title\":\"" + titles.get(2) + "\",\"value\":\"\"},"
                + "{\"code\":\"" + ChineseToEnUtil.getPingYin(titles.get(3)) + "\",\"\\\"operate_type\\\":\\\"openWeb\\\",\"title\":\"" + titles.get(3) + "\",\"value\":\"\"},"
                + "{\"code\":\"TELEPHONE\",\"\\\"operate_type\\\":\\\"staticinfo\\\",\"title\":\"联系商家\",\"value\":\"" + phone + "\"},"
                + "],"
                // 會員卡信息規則
                + "\"field_rule_list\":[{"
                // 開卡日期與過期日期
                + "\"field_name\":\"OpenDate\",\"rule_name\":\"DATE_IN_FUTURE\",\"rule_value\":\"" + new Date() + "\""
                + "}],"
                + "}");
        AlipayMarketingCardTemplateModifyResponse response = null;
        try {
            response = alipayClient.execute(request);
            System.out.println(response.getBody());
            //打印支付宝卡模板ID
            System.out.println(response.getTemplateId());
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assert response != null;
        if (response.isSuccess()) {
            System.out.println("调用成功");
            return response.getTemplateId();
        } else {
            System.out.println("调用失败");
        }
        return null;
    }

    /**
     * 會員卡開卡
     *
     * @param templateId
     * @throws AlipayApiException
     */
    public String SetCard(String templateId) throws AlipayApiException {
        AlipayMarketingCardFormtemplateSetRequest request = new AlipayMarketingCardFormtemplateSetRequest();
        request.setBizContent("{"
                + "\"template_id\":\"" + templateId + "\","
                + "\"fields\":{"
                + "\"required\":\"{\\\"common_fields\\\":[\\\"OPEN_FORM_FIELD_MOBILE\\\",\\\"OPEN_FORM_FIELD_GENDER\\\",\\\"OPEN_FORM_FIELD_NAME\\\",\\\"OPEN_FORM_FIELD_BIRTHDAY\\\"]}\","
                + "\"optional\":\"{\\\"common_fields\\\":[\\\"OPEN_FORM_FIELD_GENDER\\\"]}\""
                + "}"
                + "}");
        AlipayMarketingCardFormtemplateSetResponse response = alipayClient.execute(request);
        if (response.isSuccess()) {
            System.out.println("调用成功");
            System.out.println(response.getBody());
            return response.getBody();
        } else {
            System.out.println("调用失败");
        }
        return null;
    }
}
