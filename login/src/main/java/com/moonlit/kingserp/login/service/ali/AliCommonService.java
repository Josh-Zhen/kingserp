package com.moonlit.kingserp.login.service.ali;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;

/**
 * @Description:
 * @Author: Joshua
 * @CreateDate: 2020-06-18 11:17
 * @Version 1.0
 */
public interface AliCommonService {

    /**
     * 獲取用戶信息
     *
     * @param authCode 授權碼
     * @return
     * @throws AlipayApiException
     */
    AlipaySystemOauthTokenResponse getUser(String authCode) throws AlipayApiException;
}
