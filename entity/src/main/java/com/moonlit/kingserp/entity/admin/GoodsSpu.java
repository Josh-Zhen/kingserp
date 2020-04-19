package com.moonlit.kingserp.entity.admin;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * 商品Spu表
 *
 * @author Joshua
 * @since 2020-04-19
 */
@Data
@Entity
@Table(name = "goods_spu")
@ApiModel(value = "GoodsSpu", description = "商品Spu表")
public class GoodsSpu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id", name = "id", example = "1")
    private Long id;

    /**
     * 商品名稱
     */
    @ApiModelProperty(value = "goodsName", name = "商品名稱")
    private String goodsName;

    /**
     * 種類id
     */
    @ApiModelProperty(value = "kindId", name = "種類id", example = "1")
    private Integer kindId;

    /**
     * 总库存
     */
    @ApiModelProperty(value = "num", name = "总库存", example = "10")
    private Integer num;

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
     * 助記碼
     */
    @ApiModelProperty(value = "nameShorthand", name = "助記碼")
    private String nameShorthand;

}
