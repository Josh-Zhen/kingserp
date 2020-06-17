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
     * 应用ID,您的APPID
     **/
    public static String app_id = "2021001162611667";

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     **/
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCYbso6mFdjNgBnVwTT/+IOFeTXJj2kVZLoN5OqQ337bfZAkmtd0Wcq07/miiL5hriyfDGXZ5LyX6Y5lTbwOG4UG9hQAe5uKWNT7jb3XZRQwf6OWbGre4CaB74PwpviS6wrUfYjZBMs1txYTWDg7hufBW42g590lR+gB/7xOUcbd8j9/RiMlPcMARipMny6C0HmwNW7SKGDqrW9HgEgRfyelidq7+VwmX6JIeOl04hDVgXBppj75uC1sCJ55uPUY+vAokm2wVHLYfQ45KrLu7QRQiIExcvXvpRm0GGEVQYmhuUiYytL57d9O6o503UANrFE5SLPRfvn6N7fqbJdRfRRAgMBAAECggEAG56/j/x4oSZwzkjLPCq6aDj5XS61rqg+1Ur0u748/qj/HNYrFDXUTsNNm1VeXv5VSyoLxlZjHv3L3dU2vjJLZ+Nfv6eJ73YaZGPem9XeAQAVwu9nrR3UwJQ0kSB/JHL3wGW6htEkVFSeWnWy3y7l3Fuci84vv20h7NpmDImHY+d54Vwbf4NoMCrGrm/1AuDunOPlXxpkYM8T/WUg2vH/rWyn4qKG46YJn0DnmGkXWCE/ljIbafJw29mfbg/87EAGg48Lo/s/0MRrucnu4gZD/tf5uD630vHSyeWZ602tG9arQgB5GYUFBBf9LGDG5z2x56QEIkYHGwo2EjjntNIe0QKBgQDONRdZovx7GsRLSbuBFC0hUiQjCl08UKuS8/IIa/0E4RuonpE5XvtsV12qMLc7U0izZ5rz55UHKdtr2eBlHLch14KeuTPbBOoaDjxlcwrpiZEL0v7sncU5YOIvNjmocnMvFbtFI/7niprQLRFoFrFS1s2BnSocWmpA9tctFQ+4bQKBgQC9PZGsS21q/Eaiyp2E0whewd9p/3XD2wfT+0UPOwvshVsqPfMQvYsSnolJ1azUd6j0SPptsit1mPiJXD0D5aMzVnQKjlZ5Yid5R+sxlCpmFubGd/W8wz2imfXJ8gUyFKlBlEhJn8pRYQEwL/EKY7hDj8n7pXu2I3dOaoll1cPE9QKBgFYi904KN011MOoCMsEInJF+rnm0qp23VPRd0oySD28qWVNmaWN9uajYFdgx4Vh2wbevX5ri8e28Hlw+9u1FwXnxfC6xTmf+9YtMxw3ZsIU9Ycl+qd5NSUVeoxz1mcAzjsnsWf3Xu3MdAu5S5SKF2CyybcXm0OBnJFtewSOj2J1BAoGAUWw6T22syIl/Qu/20n2SbmLwY8UcwIZvWdAVKLZ5LO08CnLZMGy9d+dqnrAnSsupkGIODkDI3jJRTZi4UALwM1iRWTFhF50Z6t783Too/AACj4Zx3yQKUvn3fUp63c9x+9t3NuZfwKACSN930MaTM86cU4AiOH4OCVlhgMV3FDkCgYBXIdJX3CF60l8Flvc00rLZFRvSWuUdifwu+uteQBPv1XOvbFualScOCFhbKKUwNEf0A+d5FRySACBBbchFo5PhXvNFhyZwSKSI+f02Q2SLEEEz1Kq1IXR1tt22Q2KM9z2Dc9vi7XKxAd2U31rwlMqpOZqwvC5onFOCB1HWAIVTjw==";

    /**
     * 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥
     **/
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmG7KOphXYzYAZ1cE0//iDhXk1yY9pFWS6DeTqkN9+232QJJrXdFnKtO/5ooi+Ya4snwxl2eS8l+mOZU28DhuFBvYUAHubiljU+42912UUMH+jlmxq3uAmge+D8Kb4kusK1H2I2QTLNbcWE1g4O4bnwVuNoOfdJUfoAf+8TlHG3fI/f0YjJT3DAEYqTJ8ugtB5sDVu0ihg6q1vR4BIEX8npYnau/lcJl+iSHjpdOIQ1YFwaaY++bgtbAieebj1GPrwKJJtsFRy2H0OOSqy7u0EUIiBMXL176UZtBhhFUGJoblImMrS+e3fTuqOdN1ADaxROUiz0X75+je36myXUX0UQIDAQAB";

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
