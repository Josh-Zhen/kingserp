package com.moonlit.kingserp.entity.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * 菜单管理
 *
 * @author Joshua
 * @since 2020-02-08
 */
@ApiModel(value = "sysMenu", description = "菜单管理")
@Data
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    @ApiModelProperty(value = "菜单id", name = "menuId", example = "123")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuId;
    /**
     * 父菜单ID，一级菜单为0
     */
    @ApiModelProperty(value = "父菜单ID，一级菜单为0", name = "parentId", example = "1")
    private Integer parentId;
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称", name = "name")
    private String name;
    /**
     * 菜单URL
     */
    @ApiModelProperty(value = "菜单URL", name = "url")
    private String url;
    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    @ApiModelProperty(value = "授权(多个用逗号分隔，如：user:list,user:create)", name = "perms")
    private String perms;
    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
    @ApiModelProperty(value = "类型   0：目录   1：菜单   2：按钮", name = "type", example = "1")
    private Integer type;
    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标", name = "icon")
    private String icon;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", name = "orderNum", example = "1")
    private Integer orderNum;
    /**
     * 状态（0.不可用1.可用）
     */
    @ApiModelProperty(value = "状态（0.不可用1.可用）", name = "status", example = "1")
    private Integer status;

}
