package com.moonlit.kingserp.entity.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 *
 * @author Joshua
 * @since 2020-02-08
 */
@ApiModel(value = "SysRole", description = "角色")
@Data
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id", name = "roleId", example = "123")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", name = "roleName")
    private String roleName;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark")
    private String remark;
    /**
     * 创建者ID
     */
    @ApiModelProperty(value = "创建者ID", name = "createUserId", example = "123")
    private Integer createUserId;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;

    /**
     * 是否启用（0否1是）
     */
    @ApiModelProperty(value = "是否启用（0否1是）", name = "state", example = "1")
    private Integer state;

    @ApiModelProperty(value = "用户数量", name = "userCount", example = "123")
    @Transient
    private Integer userCount;

}
