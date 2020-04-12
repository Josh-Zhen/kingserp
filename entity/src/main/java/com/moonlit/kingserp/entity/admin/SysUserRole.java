package com.moonlit.kingserp.entity.admin;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户与角色对应关系
 *
 * @author Joshua
 * @since 2020-02-08
 */
@Data
@Entity
@Table(name = "sys_user_role")
@ApiModel(value = "sysUserRole", description = "用户与角色对应关系")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 角色ID
     */
    private Integer roleId;

}
