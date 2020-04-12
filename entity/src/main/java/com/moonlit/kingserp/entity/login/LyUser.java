package com.moonlit.kingserp.entity.login;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

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
     * 用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    @ApiModelProperty(value = "id", name = "id", example = "1")
    private Integer id;

    /**
     * 客户账号
     */
    @ApiModelProperty(value = "客户账号", name = "userName")
    private String userName;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", name = "nickName")
    private String nickName;

    /**
     * 性别（1-男、2-女）
     */
    @ApiModelProperty(value = "性别（1-男、2-女）", name = "sex", example = "123")
    private Integer sex;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", name = "phone")
    private String phone;

    /**
     * 所在地區
     */
    @ApiModelProperty(value = "所在地區", name = "area")
    private String area;

    /**
     * 賬號拼音簡寫
     */
    @ApiModelProperty(value = "賬號拼音簡寫", name = "nameShorthand")
    private String nameShorthand;

    /**
     * 注册时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "注册时间", name = "registrationTime")
    private Date registrationTime;

    /**
     * 消费总金额
     */
    @ApiModelProperty(value = "消费总金额", name = "sumAmount", example = "1")
    private Integer sumAmount;

    /**
     * 总订单数量
     */
    @ApiModelProperty(value = "总订单数量", name = "sumOrder", example = "1")
    private Integer sumOrder;

    /**
     * 总退货次数
     */
    @ApiModelProperty(value = "总退货次数", name = "sumEvaluation", example = "1")
    private Integer sumReturn;

    /**
     * 用户状态（0-存活、1-禁用）
     */
    @ApiModelProperty(value = "用户状态（0-存活、1-禁用）", name = "type")
    private Integer type;

    /**
     * 应收款项
     */
    @ApiModelProperty(value = "应收款项", name = "amountReceivable", example = "1")
    private Integer amountReceivable;

}
