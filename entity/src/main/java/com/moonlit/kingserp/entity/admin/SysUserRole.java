package com.moonlit.kingserp.entity.admin;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 用户与角色对应关系
 *
 * @author Joshua
 * @since 2020-02-08
 */
@ApiModel(value = "sysUserRole", description = "用户与角色对应关系")
@Data
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;
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
