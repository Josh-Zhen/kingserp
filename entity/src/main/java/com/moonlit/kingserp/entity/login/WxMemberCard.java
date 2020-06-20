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
public class WxMemberCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", value = "id", example = "1")
    private Integer id;

    /**
     * 商户表主键id
     */
    @ApiModelProperty(name = "商户表主键id", value = "merchantId")
    private Integer merchantId;

    /**
     * 商家自定义会员卡背景图
     */
    @ApiModelProperty(name = "商家自定义会员卡背景图", value = "backgroundPicUrl")
    private String backgroundPicUrl;

    /**
     * 卡券的商户logo
     */
    @ApiModelProperty(name = "卡券的商户logo", value = "logoUrl")
    private String logoUrl;

    /**
     * 商户名称（主标题，上限12个汉字）
     */
    @ApiModelProperty(name = "商户名称（主标题，上限12个汉字）", value = "brandName")
    private String brandName;

    /**
     * 卡券名（副标题，上限9个汉字）
     */
    @ApiModelProperty(name = "卡券名（副标题，上限9个汉字）", value = "title")
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
    @ApiModelProperty(name = "Code展示类型", value = "codeType")
    private String codeType;

    /**
     * 卡券使用提醒，字数上限为16个汉字。
     */
    @ApiModelProperty(name = "卡券使用提醒，字数上限为16个汉字。", value = "notice")
    private String notice;

    /**
     * 卡券使用说明，字数上限为1024个汉字。
     */
    @ApiModelProperty(name = "卡券使用说明，字数上限为1024个汉字。", value = "description")
    private String description;

    /**
     * 客服电话
     */
    @ApiModelProperty(name = "客服电话", value = "servicePhone")
    private String servicePhone;

    /**
     * 会员卡特权说明,限制1024汉字。
     */
    @ApiModelProperty(name = "会员卡特权说明,限制1024汉字。", value = "prerogative")
    private String prerogative;

    /**
     * 商家服务类型：（可多选）
     * BIZ_SERVICE_DELIVER 外卖服务； BIZ_SERVICE_FREE_PARK 停车位； BIZ_SERVICE_WITH_PET 可带宠物； BIZ_SERVICE_FREE_WIFI 免费wifi
     */
    @ApiModelProperty(name = "商家服务类型", value = "businessService")
    private String businessService;

    /**
     * 图文列表，显示在详情内页 ，优惠券券开发者须至少传入 一组图文列表
     * 格式
     * {
     * "k1":{"image_url":"圖片地址","text":"文案"},
     * "k2":{"image_url":"圖片地址","text":"文案"},
     * "k3":{"image_url":"圖片地址","text":"文案"},
     * "k4":{"image_url":"圖片地址","text":"文案"}
     * }
     */
    @ApiModelProperty(name = "图文列表，显示在详情内页 ，优惠券券开发者须至少传入 一组图文列表", value = "textImageList")
    private String textImageList;

    /**
     * 会员卡模板id
     */
    @ApiModelProperty(name = "会员卡模板id", value = "cardId")
    private String cardId;

    /**
     * 自定義會員信息欄目
     * 格式
     * {
     * "k1":{"name":"充值记录","tips":"","url":"地址"},
     * "k2":{"name":"充值记录","tips":"","url":"地址"},
     * "k3":{"name":"充值记录","tips":"","url":"地址"},
     * "k4":{"name":"充值记录","tips":"","url":"地址"}
     * }
     */
    @ApiModelProperty(name = "自定義會員信息欄目", value = "customCells")
    private String customCells;

    /**
     * 二維碼地址，需要調用二維碼轉圖片接口
     */
    @ApiModelProperty(name = "二維碼地址，需要調用二維碼轉圖片接口", value = "ticket")
    private String ticket;
}
