package com.moonlit.kingserp.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResponseSeckillEnums {

    /**
     * 请求成功
     */
    SUCCESS(200, "请求成功"),

    /**
     * 请求失败
     */
    ERROR(400, "请求失败"),

    /**
     * 未登录
     */
    FAILED_UNLOGIN(401, "未登录"),

    /**
     * 当前签名已失效
     */
    AUTHENTICATE_INVALID(401, "当前签名已失效，请重新登陆"),

    /**
     * 没有通过身份验证
     */
    FAILED_AUTHENTICATE(401, "没有通过身份验证"),

    /**
     * 不具有访问资源所需的权限
     */
    NO_ACCESS(401, "不具有访问资源所需的权限"),

    /**
     * 手机号或者验证码错误
     */
    FAILED_LOGIN(402, "手机号或者密码错误"),

    /**
     * 业务参数错误
     */
    PARAM_ERROR(402, "业务参数错误或为空"),

    /**
     * 验证码输入错误或为空
     */
    PARAM_ERROR_TELCODE(402, "验证码输入错误或为空"),

    /**
     * 手机号已被注册
     */
    PARAM_ERROR_TEL_REPEATED(402, "手机号已被注册"),

    /**
     * 所请求的资源不存在
     */
    RESOURCES_NOT_EXIST(404, "所请求的资源不存在或不可用"),

    /**
     * 请求失败 限制请求次数
     */
    FAIL_TO_REQUEST(410, "所请求的资源不再可用"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR(500, "系统异常");

    /**
     * code
     */
    private int code;

    /**
     * message
     */
    private String message;

    private static Map<Integer, ResponseSeckillEnums> map = new HashMap<>(ResponseSeckillEnums.values().length);

    static {
        for (ResponseSeckillEnums resultEnums : ResponseSeckillEnums.values()) {
            map.put(resultEnums.code, resultEnums);
        }
    }

}
