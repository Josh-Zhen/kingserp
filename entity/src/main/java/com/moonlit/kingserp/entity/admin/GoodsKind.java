package com.moonlit.kingserp.entity.admin;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * 商品種類
 *
 * @author Joshua
 * @since 2020-04-19
 */
@Data
@Entity
@Table(name = "goods_kind")
@ApiModel(value = "GoodsKind", description = "商品種類")
public class GoodsKind implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id", name = "id", example = "1")
    private Integer id;

    /**
     * 種類名称
     */
    @ApiModelProperty(value = "種類名称", name = "name")
    private String name;

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
     * 狀態(0-啓用，1禁止)
     */
    @ApiModelProperty(value = "狀態(0-啓用，1禁止)", name = "status", example = "0")
    private Integer status;

    /**
     * 助記碼
     */
    @ApiModelProperty(value = "助記碼", name = "nameShorthand")
    private String nameShorthand;

    /**
     * 序列
     */
    @ApiModelProperty(value = "序列", name = "sep")
    private Integer sep;

    /**
     * 上級Id(頂層為0)
     */
    @ApiModelProperty(value = "上級Id(頂層為0)", name = "parentId")
    private Integer parentId;

    /**
     * 該種類下有多少商品
     */
    @Transient
    @ApiModelProperty(value = "相關", name = "GoodsSpuSum", example = "0")
    private Integer goodsSpuSum;
}
