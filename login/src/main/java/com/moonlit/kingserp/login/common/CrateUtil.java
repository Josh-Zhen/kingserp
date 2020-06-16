package com.moonlit.kingserp.login.common;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayMarketingCardTemplateCreateRequest;
import com.alipay.api.response.AlipayMarketingCardTemplateCreateResponse;
import com.moonlit.kingserp.common.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @Description:
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
     * @param cardShowName 錢包端顯示名稱（如：花唄聯名卡）
     * @param logoId       logo圖片地址
     * @param backgroundId 背景圖片地址
     * @param customizeArr 自定義入口
     * @return
     */
    public void crateModel(String cardShowName, String logoId, String backgroundId, HashMap<String, ArrayList<String>> customizeArr) throws AlipayApiException {
        // 初始化
        AlipayMarketingCardTemplateCreateRequest request = new AlipayMarketingCardTemplateCreateRequest();

        // 入口標題
        ArrayList<String> titles = customizeArr.get("title");
        // 鏈接地址
        ArrayList<String> urls = customizeArr.get("Url");

        // 存放模板參數
        request.setBizContent("{" +
                "\"request_id\":\"" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + CommonUtil.getVerificationCode(5) + "\"," +
                "\"card_type\":\"OUT_MEMBER_CARD\"," +
                "\"biz_no_prefix\":\"" + new SimpleDateFormat("yyyyMMddHHmmss") + "\"," +
                "\"biz_no_suffix_len\":\"10\"," +
                "\"write_off_type\":\"qrcode\"," +
                "\"template_style_info\":{" +
                "\"card_show_name\":\"" + cardShowName + "\"," +
                "\"logo_id\":\"" + logoId + "\"," +
                // 此字段荒廢
//                "\"color\":\"rgb(55,112,179)\"," +
                "\"background_id\":\"" + backgroundId + "\"," +
                "\"bg_color\":\"rgb(55,112,179)\"," +
                "\"front_text_list_enable\":false," +
                "\"front_image_enable\":false," +
                // 領卡顯示特色信息
//                "        \"feature_descriptions\":[" +
//                "          \"特色信息，用于领卡预览\"" +
//                "        ]," +
//                "\"slogan\":\"会员权益享不停\"," +
//                "\"slogan_img_id\":\"1T8Pp00AT7eo9NoAJkMR3AAAACMAAQEC\"," +
//                "\"brand_name\":\"云臉科技\"," +
//                "\"banner_img_id\":\"1T8Pp00AT7eo9NoAJkMR3AAAACMAAQEC\"," +
//                "\"banner_url\":\"http://www.baidu.com\"," +
                // 設置會員卡權益列表
//                "\"column_info_layout\":\"list\"" +
//                "    }," +
//                "      \"template_benefit_info\":[{" +
//                "        \"title\":\"消费即折扣\"," +
//                "          \"benefit_desc\":[" +
//                "            \"权益描述信息-10\"" +
//                "          ]," +
//                "\"start_date\":\"2016-07-18 15:17:23\"," +
//                "\"end_date\":\"2016-07-34 12:12:12\"" +
//                "        }]," +
                // 欄位選擇
                "      \"column_info_list\":[{" +
                "        \"code\":\"BENEFIT_INFO\"," +
                "\"operate_type\":\"openWeb\"," +
                "\"title\":\"" + titles.get(0) + "\"," +
                "\"value\":\"80\"," +
                "\"more_info\":{" +
                "\"title\":\" " + titles.get(0) + "\"," +
                "\"url\":\"" + urls.get(0) + "\"," +
//                "\"params\":\"{}\"," +
//                "            \"descs\":[" +
//                "              \"会员生日7折\"" +
//                "            ]" +
                "        }," +
                // 如果使用 grid排序 則需要提交圖片地址 可爲空
//                "\"icon_id\":\"1T8Pp00AT7eo9NoAJkMR3AAAACMAAQEC\"," +
//                "\"tag\":\"热门\"," +
//                "\"group_title\":\"校园助手\"" +
                "        }]," +
                "      \"field_rule_list\":[{" +
                "        \"field_name\":\"OpenDate\"," +
                "\"rule_name\":\"DATE_IN_FUTURE\"," +
                "\"rule_value\":\"" + new Date() + "\"" +
                "        }]," +
//                "      \"card_action_list\":[{" +
//                "        \"code\":\"TO_CLOCK_IN\"," +
//                "\"text\":\"打卡\"," +
//                "\"url_type\":\"url\"," +
//                "\"url\":\"https://merchant.ali.com/ee/clock_in.do\"," +
                // 小程序跳轉
//                "\"mini_app_url\":{" +
//                "\"mini_app_id\":\"2018xxxxxxx\"," +
//                "\"mini_page_param\":\"xxxxxxxx\"," +
//                "\"mini_query_param\":\"abcxxxxxx\"," +
//                "\"display_on_list\":\"false\"" +
//                "        }" +
//                "        }]," +
                // 幫商戶發起開卡行爲
                "\"open_card_conf\":{" +
                "\"open_card_source_type\":\"ISV\"," +
                // 傳商戶id/商戶token
                "\"source_app_id\":\"201609191111111\"," +
                "\"open_card_url\":\"https://www.alipay.com\"," +
//                "\"conf\":\"\\\"\\\"\"," +
                // 權益内容標題
//                "        \"card_rights\":[{" +
//                "          \"title\":\"积分兑换好礼\"," +
//                "\"detail\":\"积分随时查，积分换好礼\"" +
//                "          }]" +
//                "    }," +
//                "      \"shop_ids\":[" +
//                "        \"2015122900077000000002409504\"" +
//                "      ]," +
//                "      \"pub_channels\":[{" +
//                "        \"pub_channel\":\"SHOP_DETAIL\"," +
//                "\"ext_info\":\"\\\"key\\\":\\\"value\\\"\"" +
//                "        }]," +
//                "      \"card_level_conf\":[{" +
//                "        \"level\":\"VIP1\"," +
//                "\"level_show_name\":\"黄金会员\"," +
//                "\"level_icon\":\"1T8Pp00AT7eo9NoAJkMR3AAAACMAAQEC\"," +
//                "\"level_desc\":\"黄金会员享受免费停车\"" +
//                "        }]," +
//                "\"mdcode_notify_conf\":{" +
//                "\"url\":\"https://www.ali123.com/ant/mdcode\"," +
//                "\"ext_params\":\"{\\\"param1\\\":\\\"value1\\\",\\\"param2\\\":\\\"value2\\\"}\"" +
//                "    }," +
//                "\"card_spec_tag\":\"NONE\"" +
                "  }");
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
    }

}
