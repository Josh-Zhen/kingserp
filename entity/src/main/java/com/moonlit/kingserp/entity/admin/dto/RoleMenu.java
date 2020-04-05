package com.moonlit.kingserp.entity.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Joshua
 * @CreateDate: 2020-04-05 17:39
 * @Version 1.0
 */
@Data
public class RoleMenu implements Serializable {

    @ApiModelProperty(value = "roleId", name = "roleId", example = "1")
    private Integer roleId;
    @ApiModelProperty(value = "menuIdList", name = "menuIdList")
    private String menuIdList;
}
