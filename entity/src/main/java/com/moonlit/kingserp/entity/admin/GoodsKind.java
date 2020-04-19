package com.moonlit.kingserp.entity.admin;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * 商品種類表
 *
 * @author Joshua
 * @since 2020-04-19
 */
@Data
@Entity
@Table(name = "goods_kind")
@ApiModel(value = "GoodsKind", description = "商品種類表")
public class GoodsKind implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id", name = "id", example = "1")
    private Long id;

    /**
     * 種類名称
     */
    @ApiModelProperty(value = "name", name = "種類名称")
    private String name;

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
     * 狀態(0-啓用，1禁止)
     */
    @ApiModelProperty(value = "status", name = "狀態(0-啓用，1禁止)", example = "0")
    private Integer status;

}