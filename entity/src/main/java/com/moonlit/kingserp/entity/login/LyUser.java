package com.moonlit.kingserp.entity.login;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 客戶信息
 *
 * @author Joshua
 * @since 2020-02-04
 */
@Data
@ApiModel(value = "LyUser", description = "客戶信息")
public class LyUser {

    private static final long serialVersionUID = 1L;

    /**
     * 客户账号
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别（1-男、2-女）
     */
    private Integer sex;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 注册时间
     */
    private LocalDateTime registrationTime;

    /**
     * 用户来源（0-App、1-微信，2-网页）
     */
    private Boolean source;

    /**
     * 消费总金额
     */
    private Integer sumAmount;

    /**
     * 总订单数量
     */
    private Integer sumOrder;

    /**
     * 总退货次数
     */
    private Integer sumReturn;

    /**
     * 用户状态（0-存活、1-禁用）
     */
    private Boolean type;

    /**
     * 密码盐度
     */
    private String userSalt;

}
