package com.moonlit.kingserp.entity.admin;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * 系统用户
 *
 * @author Joshua
 * @since 2020-02-08
 */
@Data
@ApiModel(value = "SysUser", description = "系统用户")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "id", name = "id", example = "123")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号", name = "userName")
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password")
    private String password;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", name = "email")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", name = "mobile")
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    @ApiModelProperty(value = "状态  0：禁用   1：正常", name = "status", example = "1")
    private Integer status;

    /**
     * 创建者ID
     */
    @ApiModelProperty(value = "创建者ID", name = "createUserId", example = "123")
    private Integer createUserId;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;

    /**
     * 是否管理员（1.是0.否）
     */
    @ApiModelProperty(value = "是否管理员（1.是0.否）", name = "userIsSuper", example = "0")
    private Integer userIsSuper;

    /**
     * 密码盐度
     */
    @JsonIgnore
    @ApiModelProperty(value = "密码盐度，为保证存储安全，密码MD5加密时需要拼接该密码盐度", name = "userSalt")
    private String userSalt;

    /**
     * 最后登录时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后登录时间", name = "lastTime")
    private Date lastTime;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像", name = "url")
    private String url;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "comment")
    private String comment;

    @Transient
    @ApiModelProperty(value = "角色名稱", name = "roleName")
    private String roleName;
    @Transient
    @ApiModelProperty(value = "角色ID", name = "roleId", example = "123")
    private Integer roleId;

    /**
     * 角色状态,
     */
    @ApiModelProperty(value = "角色状态", name = "state", example = "1")
    @Transient
    private Integer state;
}
