package com.moonlit.kingserp.login.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayObject;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayMarketingCardOpenModel;
import com.alipay.api.domain.CardUserInfo;
import com.alipay.api.domain.MerchantCard;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.moonlit.kingserp.common.util.ChineseToEnUtil;
import com.moonlit.kingserp.common.util.CommonUtil;
import com.moonlit.kingserp.login.common.AliCreateConfig;
import com.moonlit.kingserp.login.service.ali.AliCrateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.security.auth.callback.Callback;
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
@Service
public class AliCrateServiceImpl implements AliCrateService {

    //    private static AlipayClient alipayClient = new DefaultAlipayClient(AliCreateConfig.gatewayUrl, AliCreateConfig.app_id, AliCreateConfig.merchant_private_key, AliCreateConfig.format, AliCreateConfig.charset, AliCreateConfig.alipay_public_key, AliCreateConfig.sign_type);
    private static AlipayClient alipayClient = AliCreateConfig.alipayClient;

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
    @Override
    public String crateModel(String title, String logo, String backgroundId, HashMap<String, ArrayList<String>> customizeArr, String phone, String sourceAppId) {
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
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
            return null;
        }
        return response.getTemplateId();
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
    @Override
    public String updataCrate(String title, String logo, String backgroundId, HashMap<String, ArrayList<String>> customizeArr, String phone, String sourceAppId, String templateId) {
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
                + "{\"code\":\"" + ChineseToEnUtil.getPingYin(titles.get(0)) + "\",\"\\\"operate_type\\\":\\\"openWeb\\\",\"title\":\"" + titles.get(0) + "\","
                + "\"more_info\":{\"title\":\"" + titles.get(0) + "\",\"url\":\"" + urls.get(0) + "\",\"params\":\"{}\"},"
                + "{\"code\":\"" + ChineseToEnUtil.getPingYin(titles.get(1)) + "\",\"\\\"operate_type\\\":\\\"openWeb\\\",\"title\":\"" + titles.get(1) + "\",},"
                + "\"more_info\":{\"title\":\"" + titles.get(1) + "\",\"url\":\"" + urls.get(1) + "\",\"params\":\"{}\"},"
                + "{\"code\":\"" + ChineseToEnUtil.getPingYin(titles.get(2)) + "\",\"\\\"operate_type\\\":\\\"openWeb\\\",\"title\":\"" + titles.get(2) + "\",},"
                + "\"more_info\":{\"title\":\"" + titles.get(2) + "\",\"url\":\"" + urls.get(2) + "\",\"params\":\"{}\"},"
                + "{\"code\":\"" + ChineseToEnUtil.getPingYin(titles.get(3)) + "\",\"\\\"operate_type\\\":\\\"openWeb\\\",\"title\":\"" + titles.get(3) + "\",},"
                + "\"more_info\":{\"title\":\"" + titles.get(3) + "\",\"url\":\"" + urls.get(3) + "\",\"params\":\"{}\"},"
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
        } else {
            System.out.println("调用失败");
            return null;
        }
        return response.getTemplateId();
    }

    /**
     * 會員卡開卡表單
     *
     * @param templateId
     */
    @Override
    public void setCard(String templateId) {
        AlipayMarketingCardFormtemplateSetRequest request = new AlipayMarketingCardFormtemplateSetRequest();
        request.setBizContent("{"
                + "\"template_id\":\"" + templateId + "\","
                + "\"fields\":{"
                + "\"required\":\"{\\\"common_fields\\\":[\\\"OPEN_FORM_FIELD_MOBILE\\\",\\\"OPEN_FORM_FIELD_GENDER\\\",\\\"OPEN_FORM_FIELD_NAME\\\",\\\"OPEN_FORM_FIELD_BIRTHDAY\\\"]}\""
                // 用戶可選字段，可自定
//                + ",\"optional\":\"{\\\"common_fields\\\":[\\\"OPEN_FORM_FIELD_GENDER\\\"]}\""
                + "}}");
        AlipayMarketingCardFormtemplateSetResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        assert response != null;
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }

    /**
     * 獲取投放鏈接    即掃描鏈接
     *
     * @param templateId 模板卡id
     * @return 投放鏈接
     * @throws AlipayApiException
     */
    @Override
    public String cardQrcode(String templateId) {
        AlipayMarketingCardActivateurlApplyRequest request = new AlipayMarketingCardActivateurlApplyRequest();
        request.setBizContent("{"
                + "\"template_id\":\"" + templateId + "\","
                + "\"out_string\":\"" + templateId + "\","
                // 回調地址，不可帶參   該參數會返回app_id、request_id、template_id、scope、auth_code
                + "\"callback\":\"https://wenjiang.natapp4.cc/ali/authorization\""
                // 需要关注的生活号AppId。若需要在领卡页面展示“关注生活号”提示，需开通生活号并绑定会员卡。生活号快速接入详见：https://doc.open.alipay.com/docs/doc.htm?treeId=193&articleId=105933&docType=1
//                + ",\"follow_app_id\":\"20150000000000000\""
                + "}");
        AlipayMarketingCardActivateurlApplyResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        assert response != null;
        if (response.isSuccess()) {
            System.out.println("调用成功");
            // 返回投放鏈接
            System.out.println(response.getApplyCardUrl());
        } else {
            System.out.println("调用失败");
            return null;
        }
        return response.getApplyCardUrl();
    }

    /**
     * 獲取用戶提交的信息
     *
     * @param templateId  模板id
     * @param requestId   回調Id
     * @param accessToken 用戶token
     */
    @Override
    public void getUserData(String templateId, String requestId, String accessToken) {
        AlipayMarketingCardActivateformQueryRequest request = new AlipayMarketingCardActivateformQueryRequest();
        request.setBizContent("{" +
                "\"biz_type\":\"MEMBER_CARD\"," +
                "\"template_id\":\"" + templateId + "\"," +
                "\"request_id\":\"" + requestId + "\"" +
                "}");
        AlipayMarketingCardActivateformQueryResponse response = null;
        try {
            response = alipayClient.execute(request, accessToken);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        assert response != null;
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }

    /**
     * 會員卡開卡
     *
     * @param appAuthToken 用戶token
     * @param accessToken  填写表单后获取到auth_code后通过alipay.system.oauth.token接口换取到用户访问令牌accessToken
     * @param templateId   模板id
     * @param userUniId    支付寶賬號id
     * @return
     */
    @Override
    public AlipayObject openCard(String appAuthToken, String accessToken, String templateId, String userUniId) {
        // 過期時間 (需要用戶設置)
        String validDate = "2035-12-30-00:00:00";

        AlipayMarketingCardOpenRequest request = new AlipayMarketingCardOpenRequest();
        request.putOtherTextParam("app_auth_token", appAuthToken);
        request.setBizContent("{"
                + "\"out_serial_no\":\"" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + CommonUtil.getVerificationCode(5) + "\","
                + "\"card_template_id\":\"" + templateId + "\","
                + "\"card_user_info\":{"
                //支付宝账户 userid，为 2088 开头的 16 为数字
                + "\"user_uni_id\":\"" + userUniId + "\","
                + "\"user_uni_id_type\":\"UID\"},"
                + "\"card_ext_info\":{"
                + "\"open_date\":\"" + new Date() + "\",\"valid_date\":\"" + validDate + "\","
                // 卡等級
//               + "\"level\":\"VIP1\","
                // 卡積分
//               + "\"point\":\"88\","
                // 卡餘額
//               + "\"balance\":\"124.89\"},"
                // 用戶會員信息
//                + "\"member_ext_info\":{\"name\":\"萧沫\",\"gende\":\"FEMALE\",\"birth\":\"2016-06-27\",\"cell\":\"13000000000\"}"
                + "}");
        AlipayMarketingCardOpenResponse response = null;
        try {
            response = alipayClient.execute(request, accessToken);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        assert response != null;
        return response.getCardInfo();
    }
}
