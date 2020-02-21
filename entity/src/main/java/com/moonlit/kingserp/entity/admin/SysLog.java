package com.moonlit.kingserp.entity.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志
 *
 * @author Joshua
 * @since 2020-02-21
 */
@Data
@ApiModel(value = "sysLog", description = "系统日志")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "id", name = "id")
    private Long id;
    /**
     * 操作者ID
     */
    @ApiModelProperty(value = "uId", name = "操作者ID")
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
}
