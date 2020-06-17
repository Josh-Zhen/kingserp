package com.moonlit.kingserp.entity.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Joshua
 */
@Data
@Entity
@Table(name = "member_al_card")
@ApiModel(value = "MemberAlCard", description = "")
public class MemberAlCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", value = "id", example = "1")
    private Integer id;

    /**
     * 商户id
     */
    @ApiModelProperty(name = "merchantid", value = "商户id", example = "1")
    private Integer merchantid;

    /**
     * 商家LOGO
     */
    @ApiModelProperty(name = "商家LOGO", value = "logo")
    private String logo;

    /**
     * 支付宝返回的logoID
     */
    @ApiModelProperty(name = "支付宝返回的logoID", value = "logoId")
    private String logoId;

    /**
     * 主标题
     */
    @ApiModelProperty(name = "主标题 頂端顯示名稱（如：花唄聯名卡）", value = "title")
    private String title;

    /**
     * 卡等级
     */
    @ApiModelProperty(name = "卡等级", value = "agent")
    private String agent;

    /**
     * 多选 栏位展示: 1积分 2余额
     */
    @ApiModelProperty(name = "多选 栏位展示: 1积分 2余额", value = "field")
    private String field;

    /**
     * 卡片背景图
     */
    @ApiModelProperty(name = "卡片背景图", value = "img")
    private String img;

    /**
     * 支付宝返回的背景图ID
     */
    @ApiModelProperty(name = "支付宝返回的背景图ID", value = "backgroundId")
    private String backgroundId;

    /**
     * 栏位信息 自定义 存入JSON
     */
    @ApiModelProperty(name = "栏位信息 自定义 存入JSON", value = "customizeArr")
    private String customizeArr;

    /**
     * 支付宝会员卡id
     */
    @ApiModelProperty(name = "支付宝会员卡id", value = "cardId")
    private String cardId;

    /**
     * 会员卡领取地址
     */
    @ApiModelProperty(name = "会员卡领取地址", value = "cardUrl")
    private String cardUrl;

    /**
     * 是否通同步支付宝 0未同步 1已同步
     */
    @ApiModelProperty(name = "是否通同步支付宝 0未同步 1已同步", value = "status")
    private Integer status;

    /**
     * 添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "createTime", name = "添加时间")
    private Date createTime;

    /**
     * 模板ID
     */
    @ApiModelProperty(value = "templateId", name = "模板ID")
    private String templateId;
}
