package com.moonlit.kingserp.entity.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 角色与菜单对应关系
 *
 * @author Joshua
 * @since 2020-02-08
 */
@Data
@Entity
@Table(name = "sys_role_menu")
@ApiModel(value = "sysRoleMenu", description = "角色与菜单对应关系")
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id", name = "id", example = "1")
    private Integer id;
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID", name = "roleId", example = "1")
    private Integer roleId;
    /**
     * 菜单ID
     */
    @ApiModelProperty(value = "菜单ID", name = "menuId", example = "1")
    private Integer menuId;


}
