package com.moonlit.kingserp.entity.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 *
 * @author Joshua
 * @since 2020-02-08
 */
@Data
@Entity
@Table(name = "sys_role")
@ApiModel(value = "SysRole", description = "角色")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "角色id", name = "roleId", example = "1")
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
    @ApiModelProperty(value = "创建者ID", name = "createUserId", example = "1")
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

    @ApiModelProperty(value = "用户数量", name = "userCount", example = "12")
    @Transient
    private Integer userCount;

}
