package com.moonlit.kingserp.login.service.ali;

import com.alipay.api.AlipayObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Description:
 * @Author: Joshua
 * @CreateDate: 2020-06-18 11:16
 * @Version 1.0
 */
public interface AliCrateService {

    /**
     * 會員卡模板創建
     *
     * @param title        錢包端顯示名稱（如：花唄聯名卡）
     * @param logo         logo圖片地址
     * @param backgroundId 背景圖片地址
     * @param customizeArr 自定義入口
     * @param phone        商家聯係方式
     * @param sourceAppId  商家token
     * @return
     */
    String crateModel(String title, String logo, String backgroundId, HashMap<String, ArrayList<String>> customizeArr, String phone, String sourceAppId);

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
     */
    String updataCrate(String title, String logo, String backgroundId, HashMap<String, ArrayList<String>> customizeArr, String phone, String sourceAppId, String templateId);

    /**
     * 會員卡開卡表單
     *
     * @param templateId
     */
    void setCard(String templateId);

    /**
     * 獲取投放鏈接    即掃描鏈接
     *
     * @param templateId 模板卡id
     * @return 投放鏈接
     */
    String cardQrcode(String templateId);

    /**
     * 獲取用戶提交的信息
     *
     * @param templateId  模板id
     * @param requestId   回調Id
     * @param accessToken 用戶token
     */
    void getUserData(String templateId, String requestId, String accessToken);

    /**
     * 會員卡開卡
     *
     * @param appAuthToken 用戶token
     * @param accessToken  填写表单后获取到auth_code后通过alipay.system.oauth.token接口换取到用户访问令牌accessToken
     * @param templateId   模板id
     * @param userUniId    支付寶賬號id
     * @return
     */
    AlipayObject openCard(String appAuthToken, String accessToken, String templateId, String userUniId);


}
