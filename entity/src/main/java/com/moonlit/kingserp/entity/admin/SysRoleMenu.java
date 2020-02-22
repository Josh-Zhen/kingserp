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

    /**
     * id
     */
    @ApiModelProperty(value = "id", name = "id", example = "123")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID", name = "roleId", example = "123")
    private Integer roleId;
    /**
     * 菜单ID
     */
    @ApiModelProperty(value = "菜单ID", name = "menuId", example = "123")
    private Integer menuId;


}
