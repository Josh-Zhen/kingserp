package com.moonlit.kingserp.entity.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 角色与菜单对应关系
 *
 * @author Joshua
 * @since 2020-02-08
 */
@ApiModel(value = "sysRoleMenu", description = "角色与菜单对应关系")
@Data
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID", name = "roleId")
    private Integer roleId;
    /**
     * 菜单ID
     */
    @ApiModelProperty(value = "菜单ID", name = "menuId")
    private Integer menuId;


}
