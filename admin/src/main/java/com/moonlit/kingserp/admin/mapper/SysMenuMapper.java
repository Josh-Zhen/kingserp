package com.moonlit.kingserp.admin.mapper;

import com.moonlit.kingserp.common.tkmapper.MyMapper;
import com.moonlit.kingserp.entity.admin.SysMenu;
import com.moonlit.kingserp.entity.admin.SysMenuModel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 菜单管理 Mapper 接口
 *
 * @author Joshua
 * @since 2020-02-10
 */
public interface SysMenuMapper extends MyMapper<SysMenu> {

    /**
     * 根據角色id查詢對應菜單
     *
     * @param roleId
     * @return
     */
    @Select("SELECT m.* FROM sys_role r INNER JOIN sys_role_menu rm ON ( rm.role_id = r.role_id ) INNER JOIN sys_menu m ON ( rm.menu_id = m.menu_id ) WHERE r.role_id = #{roleId} ORDER BY m.parent_id ASC ,m.order_num ASC")
    List<SysMenu> getSysMenuByRolesId(Integer roleId);


    /**
     * 查詢所有目錄，根據角色id查詢目錄啓用情況（0.否 1.是）
     *
     * @param roleId
     * @return
     */
    @Select("SELECT m.*, (CASE WHEN EXISTS ( SELECT 1 FROM sys_role_menu rm WHERE rm.menu_id = m.menu_id AND rm.role_id = #{roleId} AND m.STATUS = 1) THEN '1' ELSE '0' END ) AS checked FROM sys_menu m WHERE m.type != 3 ORDER BY m.parent_id,m.order_num")
    SysMenuModel getCheckedRoleMenus(Integer roleId);
}
