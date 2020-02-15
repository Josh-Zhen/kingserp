package com.moonlit.kingserp.admin.service;

import com.moonlit.kingserp.entity.admin.SysMenu;

import java.util.List;

/**
 * 菜单管理 服务类
 *
 * @author Joshua
 * @since 2020-02-10
 */
public interface SysMenuService {

    /**
     * 根據角色id查詢權限
     *
     * @param roleId
     * @return
     */
    List<SysMenu> getSysMenuByRolesId(Integer roleId);
}
