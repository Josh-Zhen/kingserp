package com.moonlit.kingserp.entity.login;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * <p>
 * 微信会员卡
 * </p>
 *
 * @author Joshua
 * @since 2020-06-19
 */
@Data
@Entity
@Table(name = "wx_member_card")
@ApiModel(value = "WxMemberCard", description = "微信会员卡")
public class WxMemberCard implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", value = "id", example = "1")
    private Integer id;

    /**
     * 商户表主键id
     */
    private Integer merchantId;

    /**
     * 商家自定义会员卡背景图
     */
    private String backgroundPicUrl;

    /**
     * 卡券的商户logo
     */
    private String logoUrl;

    /**
     * 商户名称（主标题，上限12个汉字）
     */
    private String brandName;

    /**
     * 卡券名（副标题，上限9个汉字）
     */
    private String title;

    /**
     * Code展示类型（
     * "CODE_TYPE_TEXT" 文本
     * "CODE_TYPE_BARCODE" 一维码
     * "CODE_TYPE_QRCODE" 二维码
     * "CODE_TYPE_ONLY_QRCODE" 仅显示二维码
     * "CODE_TYPE_ONLY_BARCODE" 仅显示一维码
     * "CODE_TYPE_NONE" 不显示任何码型）
     * 默认为：CODE_TYPE_QRCODE
     */
    private String codeType;

    /**
     * 卡券使用提醒，字数上限为16个汉字。
     */
    private String notice;

    /**
     * 卡券使用说明，字数上限为1024个汉字。
     */
    private String description;

    /**
     * 客服电话
     */
    private String servicePhone;

    /**
     * 会员卡特权说明,限制1024汉字。
     */
    private String prerogative;

    /**
     * 商家服务类型：（可多选）
     * BIZ_SERVICE_DELIVER 外卖服务； BIZ_SERVICE_FREE_PARK 停车位； BIZ_SERVICE_WITH_PET 可带宠物； BIZ_SERVICE_FREE_WIFI 免费wifi
     */
    private String businessService;

    /**
     * 图文列表，显示在详情内页 ，优惠券券开发者须至少传入 一组图文列表
     * <p>
     * 格式
     * {
     * "k1":{"name":"充值记录","tips":"","url":"地址"},
     * "k2":{"name":"充值记录","tips":"","url":"地址"},
     * "k3":{"name":"充值记录","tips":"","url":"地址"},
     * "k4":{"name":"充值记录","tips":"","url":"地址"}
     * }
     */
    private String textImageList;

    /**
     * 会员卡模板id
     */
    private String cardId;

}
