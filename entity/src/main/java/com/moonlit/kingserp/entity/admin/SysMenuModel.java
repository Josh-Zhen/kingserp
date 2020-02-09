package com.moonlit.kingserp.entity.admin;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author Joshua
 * @since 2020-02-08
 */
@ApiModel(value = "sysMenuModel", description = "返回菜单给前端")
@Data
public class SysMenuModel extends SysMenu {

    private List<SysMenu> childAuthList;
}
