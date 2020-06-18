package com.moonlit.kingserp.entity.login.dto;

import lombok.Data;

/**
 * @Description: 用戶點擊開卡后支付寳返回的參數
 * @Author: Joshua
 * @CreateDate: 2020-06-18 15:35
 * @Version 1.0
 */
@Data
public class ResultDto {

    /**
     * 回傳id
     */
    private String requestId;

    /**
     * 用戶授權碼
     */
    private String authCode;

    /**
     * AppId
     */
    private String appId;

    /**
     * 擴展信息
     */
    private String outString;

    /**
     * 模板Id
     */
    private String templateId;
}
