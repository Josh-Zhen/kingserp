package com.moonlit.kingserp.entity.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品日志
 *
 * @author Joshua
 * @since 2020-05-01
 */
@Data
@Entity
@Table(name = "goods_log")
@ApiModel(value = "GoodsLog", description = "商品日志")
public class GoodsLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id", name = "id", example = "1")
    private Long id;
    /**
     * 操作者ID
     */
    @ApiModelProperty(value = "uId", name = "操作者ID", example = "1")
    private Integer uId;
    /**
     * 请求方法
     */
    @ApiModelProperty(value = "method", name = "请求方法")
    private String method;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "createDate", name = "创建时间")
    private Date createDate;
    /**
     * 日志记录
     */
    @ApiModelProperty(value = "data", name = "日志记录")
    private String data;

    @Transient
    @ApiModelProperty(value = "userName", name = "成員名")
    private String userName;
}
