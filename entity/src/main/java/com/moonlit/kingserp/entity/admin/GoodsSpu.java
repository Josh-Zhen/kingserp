package com.moonlit.kingserp.entity.admin;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * 商品Spu
 *
 * @author Joshua
 * @since 2020-04-19
 */
@Data
@Entity
@Table(name = "goods_spu")
@ApiModel(value = "GoodsSpu", description = "商品Spu")
public class GoodsSpu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id", name = "id", example = "1")
    private Integer id;

    /**
     * 編碼
     */
    @ApiModelProperty(value = "編碼", name = "code")
    private String code;

    /**
     * 商品名稱
     */
    @ApiModelProperty(value = "商品名稱", name = "goodsName")
    private String goodsName;

    /**
     * 種類id
     */
    @ApiModelProperty(value = "種類id", name = "kindId", example = "1")
    private Integer kindId;

    /**
     * 总库存
     */
    @ApiModelProperty(value = "总库存", name = "num", example = "10")
    private Integer num;

    /**
     * 狀態(0-啓用，1禁止)
     */
    @ApiModelProperty(value = "狀態(0-啓用，1禁止)", name = "status", example = "0")
    private Integer status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间", name = "updateTime")
    private Date updateTime;

    /**
     * 助記碼
     */
    @ApiModelProperty(value = "助記碼", name = "nameShorthand")
    private String nameShorthand;

}
