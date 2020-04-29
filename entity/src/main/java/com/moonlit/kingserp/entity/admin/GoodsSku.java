package com.moonlit.kingserp.entity.admin;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * 商品Sku表
 *
 * @author Joshua
 * @since 2020-04-19
 */
@Data
@Entity
@Table(name = "goods_sku")
@ApiModel(value = "GoodsSku", description = "商品Sku表")
public class GoodsSku implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id", name = "id", example = "1")
    private Integer id;

    /**
     * spu Id
     */
    @ApiModelProperty(value = "spuId", name = "spu Id", example = "10")
    private Integer spuId;

    /**
     * 重量
     */
    @ApiModelProperty(value = "weight", name = "重量", example = "10")
    private Integer weight;

    /**
     * 计量单位（克、包、瓶）
     */
    @ApiModelProperty(value = "unit", name = "计量单位（克、包、瓶）")
    private String unit;

    /**
     * 商品售價
     */
    @ApiModelProperty(value = "price", name = "商品售價", example = "10.00")
    private BigDecimal price;

    /**
     * 進貨價格
     */
    @ApiModelProperty(value = "purchasePrice", name = "進貨價格", example = "10.00")
    private BigDecimal purchasePrice;

    /**
     * 总库存
     */
    @ApiModelProperty(value = "num", name = "总库存", example = "10")
    private Integer num;

    /**
     * 总销量
     */
    @ApiModelProperty(value = "saleNum", name = "总销量", example = "1000")
    private Integer saleNum;

    /**
     * 狀態(0-啓用，1禁止)
     */
    @ApiModelProperty(value = "status", name = "狀態(0-啓用，1禁止)", example = "0")
    private Integer status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "createTime", name = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "updateTime", name = "修改时间")
    private Date updateTime;

    /**
     * 條形碼
     */
    @ApiModelProperty(value = "qrCode", name = "條形碼")
    private String qrCode;

}
