package com.moonlit.kingserp.admin.service.impl;

import com.moonlit.kingserp.admin.mapper.SysMenuMapper;
import com.moonlit.kingserp.admin.mapper.SysRoleMapper;
import com.moonlit.kingserp.admin.service.SysMenuService;
import com.moonlit.kingserp.entity.admin.SysMenu;
import com.moonlit.kingserp.entity.admin.SysRole;
import com.moonlit.kingserp.entity.admin.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理 服务实现类
 *
 * @author Joshua
 * @since 2020-02-10
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;
    @Autowired
    private SysRoleMapper roleMapper;

    /**
     * 根據角色id查詢權限
     *
     * @param roleId
     * @return
     */
    @Override
    public List<SysMenu> getSysMenuByRolesId(Integer roleId) {
        List<SysMenu> menus = new ArrayList<>();
        try {
            menus = menuMapper.getSysMenuByRolesId(roleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menus;
    }

    /**
     * 查询当前用户的权限
     *
     * @param sysUser
     * @return
     */
    @Override
    public Map<String, Object> selectMenu(SysUser sysUser) {
        Map<String, Object> map = new HashMap<>(32);
        map.put("sysUserInfo", sysUser);
        SysRole sysRole = roleMapper.getSysRole(sysUser.getId());
        if (sysRole == null) {
            map.put("sysMenu", menuMapper.getSysMenuByRolesId(null));
        } else {
            map.put("sysMenu", menuMapper.getSysMenuByRolesId(sysRole.getRoleId()));
        }
        return map;
    }

    /**
     * 查詢所有目錄，根據角色id查詢目錄啓用情況（0.否 1.是）
     *
     * @param roleId
     * @return
     */
    @Override
    public List<SysMenu> getCheckedRoleMenus(Integer roleId) {
        List<SysMenu> roleMenus = null;
        try {
            roleMenus = menuMapper.getCheckedRoleMenus(roleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roleMenus;
    }
}
