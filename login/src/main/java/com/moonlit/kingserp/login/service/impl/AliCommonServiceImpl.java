package com.moonlit.kingserp.login.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.moonlit.kingserp.login.common.AliCreateConfig;
import com.moonlit.kingserp.login.service.ali.AliCommonService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Joshua
 * @CreateDate: 2020-06-18 10:30
 * @Version 1.0
 */
@Service
public class AliCommonServiceImpl implements AliCommonService {

    //    private static AlipayClient alipayClient = new DefaultAlipayClient(AliCreateConfig.gatewayUrl, AliCreateConfig.app_id, AliCreateConfig.merchant_private_key, AliCreateConfig.format, AliCreateConfig.charset, AliCreateConfig.alipay_public_key, AliCreateConfig.sign_type);
    private static AlipayClient alipayClient = AliCreateConfig.alipayClient;

    /**
     * 獲取用戶信息
     *
     * @param authCode 授權碼
     * @return accessToken、auth_token_type、user_id(支付宝用户的唯一userId)、expires_in(访问令牌的有效时间，单位是秒。)、alipay_user_id(已废弃，请勿使用)、re_expires_in(刷新令牌的有效时间，单位是秒。)、refresh_token(刷新令牌。通过该令牌可以刷新access_token)
     * @throws AlipayApiException
     */
    @Override
    public AlipaySystemOauthTokenResponse getUser(String authCode) throws AlipayApiException {
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode(authCode);
        request.setGrantType("authorization_code");
        return alipayClient.execute(request);
    }
}
