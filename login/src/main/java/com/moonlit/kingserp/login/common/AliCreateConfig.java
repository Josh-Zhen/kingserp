package com.moonlit.kingserp.login.common;

/**
 * @Description: 设置帐户有关信息及返回路径
 * @Author: Joshua
 * @CreateDate: 2020-06-16 10:26
 * @Version 1.0
 */
public class AliCreateConfig {

    /**
     * 支付宝网关
     */
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    /**
     * 接口名稱
     */
    public static String method = "alipay.marketing.card.template.create";

    /**
     * 应用ID,您的APPID
     **/
    public static String app_id = "2021001162611667";

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     **/
    public static String merchant_private_key = "MIIEvAIBADANBgkqhki";

    /**
     * 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥
     **/
    public static String alipay_public_key = "MIIBIjA";

    /**
     * 數據格式（僅支持JSON）
     */
    public static String format = "JSON";

    /**
     * 字符编码格式
     **/
    public static String charset = "utf-8";

    /**
     * 签名方式
     **/
    public static String sign_type = "RSA2";


}
