package com.moonlit.kingserp.admin.service;

import com.moonlit.kingserp.entity.admin.SysMenu;
import com.moonlit.kingserp.entity.admin.SysUser;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询当前用户的权限
     *
     * @param sysUser
     * @return
     */
    Map<String, Object> selectMenu(SysUser sysUser);

    /**
     * 查詢所有目錄，根據角色id查詢目錄啓用情況（0.否 1.是）
     * @param roleId
     * @return
     */
    List<SysMenu> getCheckedRoleMenus(Integer roleId);
}
